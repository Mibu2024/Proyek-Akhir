package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.addCatatan

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.DatePickerHandler
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.databinding.FragmentAddDataAnakBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AddDataAnakFragment : Fragment() {
    private var _binding: FragmentAddDataAnakBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddCatatanViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddDataAnakBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(AddCatatanViewModel::class.java)

        val itemData = arguments?.getSerializable("itemData") as IbuHamilData

        binding.edTanggalLahir.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                DatePickerHandler.showDatePicker(requireContext(), binding.edTanggalLahir)
            }
        }

        binding.edTanggalLahir.setOnClickListener{
            DatePickerHandler.showDatePicker(requireContext(), binding.edTanggalLahir)
        }

        binding.edPeriksaSelanjutnya.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                DatePickerHandler.showDatePicker(requireContext(), binding.edPeriksaSelanjutnya)
            }
        }

        binding.edPeriksaSelanjutnya.setOnClickListener{
            DatePickerHandler.showDatePicker(requireContext(), binding.edPeriksaSelanjutnya)
        }

        val spinnerPolio2: Spinner = binding.spinPolio2
        val spinAdapterPolio2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterPolio2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPolio2.adapter = spinAdapterPolio2

        val spinnerPolio3: Spinner = binding.spinPolio3
        val spinAdapterPolio3 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterPolio3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPolio3.adapter = spinAdapterPolio3

        val spinnerPolio4: Spinner = binding.spinPolio4
        val spinAdapterPolio4 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterPolio4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerPolio4.adapter = spinAdapterPolio4

        val spinnerCampak: Spinner = binding.spinCampak
        val spinAdapterCampak = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterCampak.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerCampak.adapter = spinAdapterCampak

        val spinnerDpt1Dosis: Spinner = binding.spinHib1
        val spinAdapterDpt1Dosis = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterDpt1Dosis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerDpt1Dosis.adapter = spinAdapterDpt1Dosis

        val spinnerRubella1Dosis: Spinner = binding.spinRubella1
        val spinAdapterRubella1Dosis = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterRubella1Dosis.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRubella1Dosis.adapter = spinAdapterRubella1Dosis

        val spinnerRubellaDt: Spinner = binding.spinRubelladt
        val spinAdapterRubellaDt = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterRubellaDt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRubellaDt.adapter = spinAdapterRubellaDt

        val spinnerTetanus: Spinner = binding.spinTetanus
        val spinAdapterTetanus = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapterTetanus.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTetanus.adapter = spinAdapterTetanus

        binding.btnTambahCatatanAnak.setOnClickListener {
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
            val uid = itemData.uid
            val namaibu = itemData.fullname
            val tanggalSubmit = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

            if (namaAnak.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi nama anak", Toast.LENGTH_SHORT).show()
                binding.edNama.setError("Isi nama anak")
            } else if (umur.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi umur anak", Toast.LENGTH_SHORT).show()
                binding.edUmur.setError("Isi umur anak")
            } else if (beratBadan.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi berat badan anak", Toast.LENGTH_SHORT).show()
                binding.edBerat.setError("Isi berat badan anak")
            } else if (namaPemeriksa.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi nama pemeriksa", Toast.LENGTH_SHORT).show()
                binding.edNamaPemeriksa.setError("Isi nama pemeriksa")
            } else if (tanggalLahir.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tanggal lahir anak", Toast.LENGTH_SHORT).show()
                binding.edTanggalLahir.setError("Isi tanggal lahir anak")
            } else if (periksaSelanjutnya.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tanggal periksa selanjutnya", Toast.LENGTH_SHORT).show()
                binding.edPeriksaSelanjutnya.setError("Isi tanggal periksa selanjutnyaanak")
            } else {
                val formData = AddDataAnak(namaAnak, tanggalLahir, umur, beratBadan, polio2, polio3, polio4, campak, dptHib1dosis, rubella1Dosis, rubellaDt, tetanus, namaPemeriksa, periksaSelanjutnya, uid, namaibu, tanggalSubmit)

                if (uid != null) {
                    viewModel.uploadCatatanAnak(uid, formData)
                }

                viewModel.successMessage.observe(viewLifecycleOwner, { message ->
                    alertUpload(getString(R.string.success), message)
                })

                viewModel.errorMessage.observe(viewLifecycleOwner, { message ->
                    alertUpload(getString(R.string.failed), message)
                })
            }
        }

        val progressBar = binding.pbAddAnak
        this.viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
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
}