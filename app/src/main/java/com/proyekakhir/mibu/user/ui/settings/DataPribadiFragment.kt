package com.proyekakhir.mibu.user.ui.settings

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentDataPribadiBidanBinding
import com.proyekakhir.mibu.databinding.FragmentDataPribadiBinding
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.home.HomeViewModel

class DataPribadiFragment : Fragment() {
    private var _binding: FragmentDataPribadiBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private var currentUserUid = FirebaseAuth.getInstance().currentUser?.uid.toString()
    private var selectedImageUri: Uri? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataPribadiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        homeViewModel = ViewModelProvider(requireActivity(), factory).get(HomeViewModel::class.java)

        binding.edEmail.isEnabled = false
        binding.edNamaSuami.isEnabled = false
        binding.edKelahiranke.isEnabled = false
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        homeViewModel.fetchUserData()
        homeViewModel.userData.observe(viewLifecycleOwner, Observer { data ->
            binding.edFullname.setText(data?.fullname)
            binding.edAlamat.setText(data?.alamat)
            binding.edNamaSuami.setText(data?.namaSuami)
            binding.edEmail.setText(data?.email)
            binding.edTelpon.setText(data?.noTelepon)
            binding.edKelahiranke.setText(data?.kehamilanKe)

            if (!data?.profileImage.isNullOrEmpty()) {
                Glide.with(requireActivity())
                    .load(data?.profileImage)
                    .into(binding.profileImage)
            } else {
                binding.profileImage.setImageResource(R.drawable.cam_placeholder_logo)
            }
        })

        binding.btnSimpan.setOnClickListener {
            val progressDialog = ProgressDialog(context)
            progressDialog.setMessage("Updating...")
            progressDialog.setCancelable(false)
            progressDialog.show()

            val fullname = binding.edFullname.text.toString()
            val alamat = binding.edAlamat.text.toString()
            val noTelepon = binding.edTelpon.text.toString()

            val db = FirebaseFirestore.getInstance()
            val userRef = db.collection("users").document(currentUserUid)

            if (selectedImageUri != null) { // Check if an image is selected
                // Create a reference to store the image in Firestore Storage
                val storageRef = FirebaseStorage.getInstance().reference
                val imageRef = storageRef.child("profile_images/${currentUserUid}_profile_image")

                // Upload image to Firestore Storage
                imageRef.putFile(selectedImageUri!!)
                    .addOnSuccessListener { uploadTask ->
                        uploadTask.storage.downloadUrl.addOnSuccessListener { uri ->
                            // Image uploaded successfully, get download URL
                            val imageUrl = uri.toString()

                            // Create data to store in Firestore
                            val userData = hashMapOf(
                                "fullname" to fullname,
                                "alamat" to alamat,
                                "noTelepon" to noTelepon,
                                "profileImage" to imageUrl // Store the download URL in Firestore
                            )

                            // Store user data in Firestore
                            userRef.set(userData, SetOptions.merge())
                                .addOnSuccessListener {
                                    Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
                                    progressDialog.dismiss()
                                    findNavController().popBackStack()
                                }
                                .addOnFailureListener { e ->
                                    progressDialog.dismiss()
                                    Toast.makeText(requireContext(), "Update Failed", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }
                    .addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(requireContext(), "Image Upload Failed", Toast.LENGTH_SHORT).show()
                    }
            } else { // No image selected, update user data directly
                val userData = hashMapOf(
                    "fullname" to fullname,
                    "alamat" to alamat,
                    "noTelepon" to noTelepon
                    // Do not include "profileImage" key if no image selected
                )

                // Store user data in Firestore
                userRef.set(userData, SetOptions.merge())
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT).show()
                        progressDialog.dismiss()
                        findNavController().popBackStack()
                    }
                    .addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(requireContext(), "Update Failed", Toast.LENGTH_SHORT).show()
                    }
            }
        }



        binding.editProfile.setOnClickListener {
            ImagePicker.with(this)
                .crop() // Crop image(Optional), Check Customization for more option
                .compress(1024) // Final image size will be less than 1 MB(Optional)
                .maxResultSize(720, 720) // Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // Image Uri will not be null for RESULT_OK
            selectedImageUri = data?.data!!
            // Use Uri object instead of File to avoid storage permissions
            binding.profileImage.setImageURI(selectedImageUri)
        }
    }
}