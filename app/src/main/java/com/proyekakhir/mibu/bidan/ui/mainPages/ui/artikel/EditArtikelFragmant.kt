package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
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

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        val bidanArtikelViewModel =
            ViewModelProvider(this, factory).get(BidanArtikelViewModel::class.java)

        val currentItem = arguments?.getSerializable("selectedItem") as ArtikelData
        currentItem?.let {
            binding.edJudul.setText(it.judul)
            binding.edIsiArtikel.setText(it.isiArtikel)
        }

        binding.btnSaveEdit.setOnClickListener {
            val judul = binding.edJudul.text.toString()
            val isi = binding.edIsiArtikel.text.toString()

            val itemKey = currentItem.key
            val updatedArtikel = ArtikelData(judul, isi, currentItem.imageUrl, currentItem.uid, currentItem.tanggal, currentItem.nama, currentItem.key)// Replace with your logic to create updated artikel
            if (itemKey != null) {
                bidanArtikelViewModel.updateArtikel(itemKey, updatedArtikel) { success ->
                    if (success) {
                        alertUpload(getString(R.string.success), getString(R.string.upload_success))
                        dialog?.dismiss()
                    } else {
                        alertUpload(getString(R.string.failed), getString(R.string.upload_failed))
                        dialog?.dismiss()
                    }
                }
            }
        }

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
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
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