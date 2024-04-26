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
import com.google.common.base.Strings.isNullOrEmpty
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.databinding.FragmentAddCatatanKesehatanBinding
import com.proyekakhir.mibu.databinding.FragmentAddCatatanNifasBinding

class AddCatatanNifasFragment : Fragment() {
    private var _binding: FragmentAddCatatanNifasBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddCatatanViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCatatanNifasBinding.inflate(inflater, container, false)
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

        val spinner: Spinner = binding.spinVitA
        val spinAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, arrayOf("Sudah", "Belum"))
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinAdapter

        binding.btnTambahCatatanNifas.setOnClickListener {
            val tanggalPeriksa = binding.edTanggalPeriksa.text.toString()
            val kunjunganKe = binding.edKunjungan.text.toString()
            val periksaAsi = binding.edPeriksaAsi.text.toString()
            val periksaPendarahan = binding.edPeriksaPendarahan.text.toString()
            val periksaJalanLahir = binding.edJalanLahir.text.toString()
            val vitaminA = binding.spinVitA.selectedItem.toString()
            val masalah = binding.edMasalah.text.toString()
            val tindakan = binding.edTindakan.text.toString()

            if (tanggalPeriksa.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tanggal periksa", Toast.LENGTH_SHORT).show()
                binding.edTanggalPeriksa.setError("Isi tanggal periksa")
            } else if (kunjunganKe.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi kunjungan", Toast.LENGTH_SHORT).show()
                binding.edKunjungan.setError("Isi kunjungan")
            } else if (periksaAsi.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi hasil periksa ASI", Toast.LENGTH_SHORT).show()
                binding.edPeriksaAsi.setError("Isi hasil periksa ASI")
            } else if (periksaPendarahan.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi hasil periksa pendarahan", Toast.LENGTH_SHORT).show()
                binding.edPeriksaPendarahan.setError("Isi hasil periksa pendarahan")
            } else if (masalah.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi masalah", Toast.LENGTH_SHORT).show()
                binding.edMasalah.setError("Isi masalah")
            } else if (tindakan.isNullOrEmpty()){
                Toast.makeText(requireContext(), "Isi tindakan", Toast.LENGTH_SHORT).show()
                binding.edTindakan.setError("Isi tindakan")
            } else if (periksaJalanLahir.isNullOrEmpty()) {
                Toast.makeText(requireContext(), "Isi hasil periksa jalan lahir", Toast.LENGTH_SHORT).show()
                binding.edJalanLahir.setError("Isi hasil periksa jalan lahir")
            } else {
                val uid = itemData.uid
                val nama = itemData.fullname
                val formData = AddNifasData(tanggalPeriksa, kunjunganKe, periksaAsi, periksaPendarahan, periksaJalanLahir, vitaminA, masalah, tindakan, uid, nama)

                if (uid != null) {
                    viewModel.uploadCatatanNifas(uid, formData)
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

        val progressBar = binding.pbAddNifas
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