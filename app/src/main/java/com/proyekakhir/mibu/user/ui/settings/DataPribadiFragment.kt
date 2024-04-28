package com.proyekakhir.mibu.user.ui.settings

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
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
            alertEditProfile(getString(R.string.warning), getString(R.string.want_to_edit_profile))
        }



        binding.editProfile.setOnClickListener {
            ImagePicker.with(this)
                .crop() // Crop image(Optional), Check Customization for more option
                .compress(1024) // Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    720,
                    720
                ) // Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        return root
    }

    private fun alertEditProfile(titleFill: String, descFill: String) {
        val builder = AlertDialog.Builder(requireContext())

        val customView = LayoutInflater.from(requireContext())
            .inflate(R.layout.custom_layout_dialog_2_option, null)
        builder.setView(customView)

        val title = customView.findViewById<TextView>(R.id.tv_title)
        val desc = customView.findViewById<TextView>(R.id.tv_desc)
        val btnYes = customView.findViewById<Button>(R.id.yes_btn_id)
        val btnNo = customView.findViewById<Button>(R.id.no_btn_id)

        title.text = titleFill
        desc.text = descFill

        val dialog = builder.create()

        btnYes.setOnClickListener {
            editProfile()
            dialog.dismiss()
        }

        btnNo.setOnClickListener {
            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun editProfile() {
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
                                Toast.makeText(
                                    requireContext(),
                                    "Update Success",
                                    Toast.LENGTH_SHORT
                                ).show()
                                progressDialog.dismiss()
                                findNavController().popBackStack()
                            }
                            .addOnFailureListener { e ->
                                progressDialog.dismiss()
                                Toast.makeText(
                                    requireContext(),
                                    "Update Failed",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                    }
                }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(), "Image Upload Failed", Toast.LENGTH_SHORT)
                        .show()
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
                    Toast.makeText(requireContext(), "Update Success", Toast.LENGTH_SHORT)
                        .show()
                    progressDialog.dismiss()
                    findNavController().popBackStack()
                }
                .addOnFailureListener { e ->
                    progressDialog.dismiss()
                    Toast.makeText(requireContext(), "Update Failed", Toast.LENGTH_SHORT).show()
                }
        }
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