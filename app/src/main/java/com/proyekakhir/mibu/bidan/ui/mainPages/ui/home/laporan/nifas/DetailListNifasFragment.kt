package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan.nifas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
import com.proyekakhir.mibu.databinding.FragmentDetailListNifasBinding
import com.proyekakhir.mibu.databinding.FragmentDetailNifasBinding
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel

class DetailListNifasFragment : Fragment() {
    private var _binding: FragmentDetailListNifasBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailListNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as AddNifasData

        binding.tvKunjunganValue.text = ": ${itemData.kunjunganKe}"
        binding.tvPeriksaAsiValue.text = ": ${itemData.periksaAsi}"
        binding.tvPendarahanValue.text = ": ${itemData.periksaPendarahan}"
        binding.tvJalanLahirValue.text = ": ${itemData.periksaJalanLahir}"
        binding.tvVitaValue.text = ": ${itemData.vitaminA}"
        binding.tvMasalahValue.text = ": ${itemData.masalah}"
        binding.tvTindakanValue.text = ": ${itemData.tindakan}"
        binding.tvTanggal.text = itemData.tanggalPeriksa
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return root
    }
}