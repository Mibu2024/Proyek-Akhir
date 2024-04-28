package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.edit

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
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.BidanArtikelViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.EditArtikelFragmant
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.addCatatan.AddCatatanViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.databinding.FragmentEditArtikelFragmantBinding
import com.proyekakhir.mibu.databinding.FragmentEditKesehatanBinding

class EditKesehatanFragment : DialogFragment() {
    private var _binding: FragmentEditKesehatanBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: EditKesehatanDialogListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditKesehatanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        val viewModel =
            ViewModelProvider(this, factory).get(AddCatatanViewModel::class.java)

        val currentItem = arguments?.getSerializable("selectedItem") as AddKesehatanKehamilanData
        currentItem?.let {
            binding.edKeluhan.setText(it.keluhan)
            binding.edTekananDarah.setText(it.tekananDarah)
            binding.edBeratBadan.setText(it.beratBadan)
            binding.edUmurKehamilan.setText(it.umurKehamilan)
            binding.edTinggiFundus.setText(it.tinggiFundus)
            binding.edLetakJanin.setText(it.letakJanin)
            binding.edDetakJanin.setText(it.denyutJanin)
            binding.edHasilLab.setText(it.hasilLab)
            binding.edTindakan.setText(it.tindakan)
            binding.edNasihat.setText(it.nasihat)
            binding.edTanggalPeriksaSelanjutnya.setText(it.periksaSelanjutnya)
        }

        binding.btnSaveEdit.setOnClickListener {
            val keluhan = binding.edKeluhan.text.toString()
            val tekananDarah = binding.edTekananDarah.text.toString()
            val beratBadan = binding.edBeratBadan.text.toString()
            val umurKehamilan = binding.edUmurKehamilan.text.toString()
            val tinggiFundus = binding.edTinggiFundus.text.toString()
            val letakJanin = binding.edLetakJanin.text.toString()
            val detakJanin = binding.edDetakJanin.text.toString()
            val hasilLab = binding.edHasilLab.text.toString()
            val tindakan = binding.edTindakan.text.toString()
            val nasihat = binding.edNasihat.text.toString()
            val periksaSelanjutnya = binding.edTanggalPeriksaSelanjutnya.text.toString()

            val itemKey = currentItem.key
            val updatedKesehatan = AddKesehatanKehamilanData(currentItem.tanggalPeriksa, keluhan,
                tekananDarah, beratBadan, umurKehamilan, tinggiFundus, letakJanin, detakJanin, hasilLab,
                tindakan, currentItem.kakiBengkak, nasihat, currentItem.uid, currentItem.nama, currentItem.namaPemeriksa,
                currentItem.tempatPeriksa, periksaSelanjutnya, currentItem.key, currentItem.firstChildKey)

            if (itemKey != null) {
                val uid = currentItem.uid
                if (uid != null) {
                    viewModel.updateKesehatan(uid, itemKey, updatedKesehatan) { success ->
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

    interface EditKesehatanDialogListener {
        fun onDialogPositiveClick(artikel: AddKesehatanKehamilanData)
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