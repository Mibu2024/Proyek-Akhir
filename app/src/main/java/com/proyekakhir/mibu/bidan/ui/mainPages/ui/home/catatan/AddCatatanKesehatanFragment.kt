package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.BidanSettingsViewModel
import com.proyekakhir.mibu.databinding.FragmentAddCatatanKesehatanBinding
import com.proyekakhir.mibu.databinding.FragmentDetailIbuBinding

class AddCatatanKesehatanFragment : Fragment() {
    private var _binding: FragmentAddCatatanKesehatanBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddCatatanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCatatanKesehatanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(requireActivity(), factory).get(AddCatatanViewModel::class.java)

        val itemData = arguments?.getSerializable("itemData") as IbuHamilData

        binding.edTanggalPeriksa.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                DatePickerHandler.showDatePicker(requireContext(), binding.edTanggalPeriksa)
            }
        }

        binding.edTanggalPeriksa.setOnClickListener{
            DatePickerHandler.showDatePicker(requireContext(), binding.edTanggalPeriksa)
        }

        val spinner: Spinner = binding.spinKakiBengkak
        val spinAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Ya", "Tidak"))
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinAdapter

        binding.btnTambahCatatanKesehatan.setOnClickListener {
            val tanggalPeriksa = binding.edTanggalPeriksa.text.toString()
            val keluhan = binding.edKeluhan.text.toString()
            val tekananDarah = binding.edTekananDarah.text.toString()
            val beratBadan = binding.edBeratBadan.text.toString()
            val umurKehamilan = binding.edUmurKehamilan.text.toString()
            val tinggiFundus = binding.edTinggiFundus.text.toString()
            val letakJanin = binding.edLetakJanin.text.toString()
            val denyutJanin = binding.edDetakJanin.text.toString()
            val hasilLab = binding.edHasilLab.text.toString()
            val tindakan = binding.edTindakan.text.toString()
            val kakiBengkak = binding.spinKakiBengkak.selectedItem.toString()
            val nasihat = binding.edNasihat.text.toString()

            if (tanggalPeriksa.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tanggal periksa", Toast.LENGTH_SHORT).show()
                binding.edTanggalPeriksa.setError("Isi tanggal periksa")
            } else if (keluhan.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi keluhan pasien", Toast.LENGTH_SHORT).show()
                binding.edKeluhan.setError("Isi keluhan pasien")
            } else if (tekananDarah.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tekanan darah pasien", Toast.LENGTH_SHORT).show()
                binding.edTekananDarah.setError("Isi tekanan darah pasien")
            } else if (beratBadan.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi berat badan pasien", Toast.LENGTH_SHORT).show()
                binding.edBeratBadan.setError("Isi berat badan pasien")
            } else if (umurKehamilan.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi umur kehamilan pasien", Toast.LENGTH_SHORT).show()
                binding.edUmurKehamilan.setError("Isi umur kehamilan pasien")
            } else if (tinggiFundus.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tinggi fundus pasien", Toast.LENGTH_SHORT).show()
                binding.edTinggiFundus.setError("Isi tinggi fundus pasien")
            } else if (letakJanin.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi letak janin pasien", Toast.LENGTH_SHORT).show()
                binding.edLetakJanin.setError("Isi letak janin pasien")
            } else if (denyutJanin.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi denyut jantung janin", Toast.LENGTH_SHORT).show()
                binding.edDetakJanin.setError("Isi denyut jantung janin")
            } else if (tindakan.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tindakan", Toast.LENGTH_SHORT).show()
                binding.edTindakan.setError("Isi tindakan")
            } else {
                val uid = itemData.uid
                val nama = itemData.fullname
                val formData = AddKesehatanKehamilanData(tanggalPeriksa, keluhan, tekananDarah, beratBadan, umurKehamilan, tinggiFundus, letakJanin, denyutJanin, hasilLab, tindakan, kakiBengkak, nasihat, uid, nama)

                if (uid != null) {
                    viewModel.uploadCatatanKesehatan(uid, formData)
                }

                viewModel.successMessage.observe(viewLifecycleOwner, { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    parentFragmentManager.popBackStack()
                })

                viewModel.errorMessage.observe(viewLifecycleOwner, { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                })
            }

        }

        val progressBar = binding.pbAddKesehatan
        this.viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        return root
    }
}