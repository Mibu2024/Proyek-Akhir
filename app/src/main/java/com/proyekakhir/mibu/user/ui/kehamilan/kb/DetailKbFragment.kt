package com.proyekakhir.mibu.user.ui.kehamilan.kb

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.proyekakhir.mibu.databinding.FragmentDetailKbBinding
import com.proyekakhir.mibu.user.api.response.DataLayananKbsItem

class DetailKbFragment : Fragment() {
    private var _binding: FragmentDetailKbBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailKbBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as? DataLayananKbsItem

        binding.tvTekananDarahValue.text = "${itemData?.tekananDarah} mmHg"
        binding.tvBeratBadanValue.text = "${itemData?.beratBadan} Kg"
        binding.tvNamaValue.text = "${itemData?.namaIbu}"
        binding.tvTanggalPelayananValue.text = "${itemData?.tanggalPraktik}"
        binding.tvTanggalKembaliValue.text = "${itemData?.tanggalKembali}"
        binding.tvKbValue.text = "${itemData?.jenisKb}"
        binding.tvTanggal.text = "${itemData?.tanggalPraktik}"
        binding.tvKeluhanValue.text = "${itemData?.keluhan}"
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return root
    }
}