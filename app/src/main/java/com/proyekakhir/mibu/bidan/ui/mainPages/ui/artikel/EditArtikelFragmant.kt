package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentEditArtikelFragmantBinding

class EditArtikelFragmant : DialogFragment() {
    private var _binding: FragmentEditArtikelFragmantBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: EditArtikelDialogListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditArtikelFragmantBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val currentItem = arguments?.getSerializable("selectedItem") as ArtikelData
        currentItem?.let {
            binding.edJudul.setText(it.judul)
            binding.edIsiArtikel.setText(it.isiArtikel)
        }

        binding.btnSaveEdit.setOnClickListener {
            val judul = binding.edJudul.text.toString()
            val isi = binding.edIsiArtikel.text.toString()


        }

        return root
    }

    interface EditArtikelDialogListener {
        fun onDialogPositiveClick(artikel: ArtikelData)
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }
}