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
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.addCatatan.AddCatatanViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.databinding.FragmentEditAnakBinding
import com.proyekakhir.mibu.databinding.FragmentEditKesehatanBinding

class EditAnakFragment : DialogFragment() {
    private var _binding: FragmentEditAnakBinding? = null
    private val binding get() = _binding!!
    lateinit var listener: EditAnakDialogListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditAnakBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        val viewModel =
            ViewModelProvider(this, factory).get(AddCatatanViewModel::class.java)

        val currentItem = arguments?.getSerializable("selectedItem") as AddDataAnak
        currentItem?.let {
            val spinnerPolio2: Spinner = binding.spinPolio2
            val spinAdapterPolio2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterPolio2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPolio2.adapter = spinAdapterPolio2
            spinnerPolio2.setSelection(if (currentItem.dptHb1Polio2 == "Sudah") 0 else 1) // Set selection based on currentItem data

            val spinnerPolio3: Spinner = binding.spinPolio3
            val spinAdapterPolio3 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterPolio3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPolio3.adapter = spinAdapterPolio3
            spinnerPolio3.setSelection(if (currentItem.dptHb2Polio3 == "Sudah") 0 else 1)

            val spinnerPolio4: Spinner = binding.spinPolio4
            val spinAdapterPolio4 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterPolio4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerPolio4.adapter = spinAdapterPolio4
            spinnerPolio4.setSelection(if (currentItem.dptHb3Polio4 == "Sudah") 0 else 1)

            val spinnerCampak: Spinner = binding.spinCampak
            val spinAdapterCampak = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterCampak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCampak.adapter = spinAdapterCampak
            spinnerCampak.setSelection(if (currentItem.campak == "Sudah") 0 else 1)

            val spinnerDpt1Dosis: Spinner = binding.spinHib1
            val spinAdapterDpt1Dosis = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterDpt1Dosis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerDpt1Dosis.adapter = spinAdapterDpt1Dosis
            spinnerDpt1Dosis.setSelection(if (currentItem.dptHb1Dosis == "Sudah") 0 else 1)

            val spinnerRubella1Dosis: Spinner = binding.spinRubella1
            val spinAdapterRubella1Dosis = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterRubella1Dosis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerRubella1Dosis.adapter = spinAdapterRubella1Dosis
            spinnerRubella1Dosis.setSelection(if (currentItem.campakRubella1Dosis == "Sudah") 0 else 1)

            val spinnerRubellaDt: Spinner = binding.spinRubelladt
            val spinAdapterRubellaDt = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterRubellaDt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerRubellaDt.adapter = spinAdapterRubellaDt
            spinnerRubellaDt.setSelection(if (currentItem.campakRubellaDt == "Sudah") 0 else 1)

            val spinnerTetanus: Spinner = binding.spinTetanus
            val spinAdapterTetanus = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
            spinAdapterTetanus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTetanus.adapter = spinAdapterTetanus
            spinnerTetanus.setSelection(if (currentItem.tetanus == "Sudah") 0 else 1)

            binding.edNama.setText(it.namaAnak)
            binding.edTanggalLahir.setText(it.tanggalLahir)
            binding.edUmur.setText(it.umur)
            binding.edBerat.setText(it.beratBadan)
            binding.edNamaPemeriksa.setText(it.namaPemeriksa)
            binding.edPeriksaSelanjutnya.setText(it.periksaSelanjutnya)
        }

        binding.btnSaveEdit.setOnClickListener {
            val namaAnak = binding.edNama.text.toString()
            val tanggalLahir = binding.edTanggalLahir.text.toString()
            val umur = binding.edUmur.text.toString()
            val beratBadan = binding.edBerat.text.toString()
            val polio2 = binding.spinPolio2.selectedItem.toString()
            val polio3 = binding.spinPolio3.selectedItem.toString()
            val polio4 = binding.spinPolio4.selectedItem.toString()
            val campak = binding.spinCampak.selectedItem.toString()
            val dptHib1dosis = binding.spinHib1.selectedItem.toString()
            val rubella1Dosis = binding.spinRubella1.selectedItem.toString()
            val rubellaDt = binding.spinRubelladt.selectedItem.toString()
            val tetanus = binding.spinTetanus.selectedItem.toString()
            val namaPemeriksa = binding.edNamaPemeriksa.text.toString()
            val periksaSelanjutnya = binding.edPeriksaSelanjutnya.text.toString()

            val itemKey = currentItem.key
            val updatedAnak = AddDataAnak(namaAnak, tanggalLahir, umur, beratBadan, polio2, polio3, polio4,
                campak, dptHib1dosis, rubella1Dosis, rubellaDt, tetanus, namaPemeriksa, periksaSelanjutnya,
                currentItem.uid, currentItem.namaIbu, currentItem.key, currentItem.firstChildKey)

            if (itemKey != null) {
                val uid = currentItem.uid
                if (uid != null) {
                    viewModel.updateAnak(uid, itemKey, updatedAnak) { success ->
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

    interface EditAnakDialogListener {
        fun onDialogPositiveClick(anak: AddDataAnak)
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