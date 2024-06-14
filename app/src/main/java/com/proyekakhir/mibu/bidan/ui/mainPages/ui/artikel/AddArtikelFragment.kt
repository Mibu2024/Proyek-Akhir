package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.app.Activity
import android.app.AlertDialog
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.github.dhaval2404.imagepicker.ImagePicker
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.databinding.FragmentAddArtikelBinding

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
        viewModel =
            ViewModelProvider(requireActivity(), factory).get(BidanArtikelViewModel::class.java)

        binding.layoutAddGambar.setOnClickListener {
            ImagePicker.with(this)
                .crop() // Crop image(Optional), Check Customization for more option
                .compress(1024) // Final image size will be less than 1 MB(Optional)
                .maxResultSize(
                    720,
                    720
                ) // Final image resolution will be less than 1080 x 1080(Optional)
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
                        alertUpload(getString(R.string.success), getString(R.string.upload_success))
                    },
                    onFailure = { e ->
                        alertUpload(getString(R.string.failed), getString(R.string.upload_failed))
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

    private fun alertUpload(titleFill: String, descFill: String) {
        val builder = AlertDialog.Builder(requireContext())

        val customView = LayoutInflater.from(requireContext())
            .inflate(R.layout.custom_layout_dialog_1_option, null)
        builder.setView(customView)

        val title = customView.findViewById<TextView>(R.id.tv_title)
        val desc = customView.findViewById<TextView>(R.id.tv_desc)
        val btnOk = customView.findViewById<Button>(R.id.ok_btn_id)

        title.text = titleFill
        desc.text = descFill

        val dialog = builder.create()

        btnOk.setOnClickListener {
            findNavController().popBackStack()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
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