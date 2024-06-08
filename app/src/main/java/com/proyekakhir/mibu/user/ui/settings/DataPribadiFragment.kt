package com.proyekakhir.mibu.user.ui.settings

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.storage.FirebaseStorage
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.network.NetworkConnection
import com.proyekakhir.mibu.databinding.FragmentDataPribadiBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.home.HomeViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DataPribadiFragment : Fragment() {
    private var _binding: FragmentDataPribadiBinding? = null
    private val binding get() = _binding!!
    private var currentUserUid = FirebaseAuth.getInstance().currentUser?.uid.toString()
    private var selectedImageUri: Uri? = null
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDataPribadiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.edEmail.isEnabled = false
        binding.edNamaSuami.isEnabled = false
        binding.edKelahiranke.isEnabled = false
        binding.edTelpon.isEnabled = false
        binding.edFullname.isEnabled = false
        binding.edAlamat.isEnabled = false
        binding.btnSimpan.visibility = View.GONE
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel.ibu.observe(viewLifecycleOwner, Observer { response ->
            lifecycleScope.launch {
                val dataStore: DataStore<Preferences> = requireContext().dataStore
                val userPreference = UserPreference.getInstance(dataStore)
                val userId = userPreference.getSession().firstOrNull()?.id ?: 0
                Log.d("useridget", userId.toString())

                // Filter the list based on the user ID
                val filteredList = response.dataIbuHamils?.filter { it?.id == userId } ?: emptyList()
                Log.d("kesehatanapi", filteredList.toString())

                // Check if the filtered list has any items
                if (filteredList.isNotEmpty()) {
                    // Assuming there's only one item in the filtered list
                    val ibuHamil = filteredList[0]
                    Log.d("profilibu", ibuHamil.toString())
                    binding.edEmail.setText(ibuHamil?.email)
                    binding.edFullname.setText(ibuHamil?.namaIbu)
                    binding.edAlamat.setText(ibuHamil?.alamat)
                    binding.edNamaSuami.setText(ibuHamil?.namaSuami)
                    binding.edTelpon.setText(ibuHamil?.noTelepon)
                    binding.edKelahiranke.setText(ibuHamil?.kehamilanKe.toString())
                } else {
                    Log.e("getHplDate", "No matching data found for user ID: $userId")
                }
            }
        })

        return root
    }
}