package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.edit

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.addCatatan.AddCatatanViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.databinding.FragmentEditNifasBinding

class EditNifasFragment : DialogFragment() {
    private var _binding: FragmentEditNifasBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: EditNifasDialogListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        val viewModel =
            ViewModelProvider(this, factory).get(AddCatatanViewModel::class.java)

        val currentItem = arguments?.getSerializable("selectedItem") as AddNifasData
        currentItem?.let {
            binding.edKunjungan.setText(it.kunjunganKe)
            binding.edMasalah.setText(it.masalah)
            binding.edPeriksaAsi.setText(it.periksaAsi)
            binding.edPeriksaPendarahan.setText(it.periksaPendarahan)
            binding.edJalanLahir.setText(it.periksaJalanLahir)
            binding.edVita.setText(it.vitaminA)
            binding.edTindakan.setText(it.tindakan)
        }

        binding.btnSaveEdit.setOnClickListener {
            val kunjungan = binding.edKunjungan.text.toString()
            val masalah = binding.edMasalah.text.toString()
            val periksaAsi = binding.edPeriksaAsi.text.toString()
            val pendarahan = binding.edPeriksaPendarahan.text.toString()
            val jalanLahir = binding.edJalanLahir.text.toString()
            val vitA = binding.edVita.text.toString()
            val tindakan = binding.edTindakan.text.toString()

            val itemKey = currentItem.key
            val updatedNifas = AddNifasData(
                currentItem.tanggalPeriksa, kunjungan, periksaAsi,
                pendarahan, jalanLahir, vitA, masalah, tindakan, currentItem.uid, currentItem.nama,
                currentItem.key, currentItem.firstChildKey
            )

            if (itemKey != null) {
                val uid = currentItem.uid
                if (uid != null) {
                    viewModel.updateNifas(uid, itemKey, updatedNifas) { success ->
                        if (success) {
                            alertUpload(
                                getString(R.string.success),
                                getString(R.string.upload_success)
                            )
                            dialog?.dismiss()
                        } else {
                            alertUpload(
                                getString(R.string.failed),
                                getString(R.string.upload_failed)
                            )
                            dialog?.dismiss()
                        }
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

        val dialogAlert = builder.create()

        btnOk.setOnClickListener {
            dialogAlert.dismiss()
        }

        dialogAlert.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogAlert.show()
    }

    interface EditNifasDialogListener {
        fun onDialogPositiveClick(artikel: AddNifasData)
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