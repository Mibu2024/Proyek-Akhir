package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.app.Activity
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
import com.github.dhaval2404.imagepicker.ImagePicker
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.BidanHomeViewModel
import com.proyekakhir.mibu.databinding.FragmentAddArtikelBinding
import com.proyekakhir.mibu.databinding.FragmentBidanArtikelBinding

class AddArtikelFragment : Fragment() {
    private var _binding: FragmentAddArtikelBinding? = null
    private val binding get() = _binding!!
    private var selectedImageUri: Uri? = null
    private lateinit var viewModel: BidanArtikelViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddArtikelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(BidanArtikelViewModel::class.java)

        binding.layoutAddGambar.setOnClickListener {
            ImagePicker.with(this)
                .crop() // Crop image(Optional), Check Customization for more option
                .compress(1024) // Final image size will be less than 1 MB(Optional)
                .maxResultSize(720, 720) // Final image resolution will be less than 1080 x 1080(Optional)
                .start()
        }

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnSimpan.setOnClickListener {
            val judul = binding.edJudul.text.toString()
            val isi = binding.edIsi.text.toString()

            if (judul.isNullOrEmpty()) {
                Toast.makeText(context, "Isi Judul Artikel!", Toast.LENGTH_SHORT).show()
            } else if (isi.isNullOrEmpty()) {
                Toast.makeText(context, "Isi Artikel!", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.uploadArtikel(judul, isi, selectedImageUri,
                    onSuccess = {
                        findNavController().popBackStack()
                        Toast.makeText(context, "Article saved to database.", Toast.LENGTH_SHORT).show()
                    },
                    onFailure = { e ->
                        Toast.makeText(context, "Failed to save article: ${e.message}", Toast.LENGTH_SHORT).show()
                    })
            }
        }

        val progressBar = binding.pbAddArtikel
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            // Image Uri will not be null for RESULT_OK
            selectedImageUri = data?.data!!
            // Use Uri object instead of File to avoid storage permissions
            binding.ivPreviewPoster.setImageURI(selectedImageUri)
        }
    }

}