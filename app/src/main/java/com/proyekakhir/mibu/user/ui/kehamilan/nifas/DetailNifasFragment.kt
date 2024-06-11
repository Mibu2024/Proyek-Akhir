package com.proyekakhir.mibu.user.ui.kehamilan.nifas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.proyekakhir.mibu.databinding.FragmentDetailNifasBinding
import com.proyekakhir.mibu.user.api.response.DataNifasItem

class DetailNifasFragment : Fragment() {
    private var _binding: FragmentDetailNifasBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as DataNifasItem

        binding.tvKunjunganValue.text = "${itemData.kunjunganNifas}"
        binding.tvPeriksaAsiValue.text = "${itemData.hasilPeriksaPayudara}"
        binding.tvPendarahanValue.text = "${itemData.hasilPeriksaPendarahan}"
        binding.tvJalanLahirValue.text = "${itemData.hasilPeriksaJalanLahir}"
        binding.tvVitaValue.text = "${itemData.vitaminA}"
        binding.tvMasalahValue.text = "${itemData.masalah}"
        binding.tvTindakanValue.text = "${itemData.tindakan}"
        binding.tvTanggal.text = itemData.tanggal
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return root
    }

}