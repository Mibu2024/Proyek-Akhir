package com.proyekakhir.mibu.user.ui.kehamilan.kesehatan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.databinding.FragmentDetailKesehatanBinding
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel

class DetailKesehatanFragment : Fragment() {
    private var _binding: FragmentDetailKesehatanBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailKesehatanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as? KesehatanModel

        binding.tvTekananDarah.text = ": ${itemData?.tekananDarah} mmHg"
        binding.tvBeratBadan.text = ": ${itemData?.beratBadan} Kg"
        binding.tvUmurKehamilan.text = ": ${itemData?.umurKehamilan} Minggu"
        binding.tvTinggiFundus.text = ": ${itemData?.tinggiFundus} Cm"
        binding.tvLetakJanin.text = ": ${itemData?.letakJanin}"
        binding.tvDenyutJanin.text = ": ${itemData?.denyutJanin} Bpm"
        binding.tvKakiBengkakValue.text = ": ${itemData?.kakiBengkak}"
        binding.tvTindakanValue.text = ": ${itemData?.hasilLab}"
        binding.tvHasilLabValue.text = ": ${itemData?.tindakan}"
        binding.tvKeluhanValue.text = "${itemData?.keluhan}"
        binding.tvNamaPemeriksaValue.text = ": ${itemData?.namaPemeriksa}"
        binding.tvTempatPeriksaValue.text = ": ${itemData?.tempatPeriksa}"
        binding.tvTglPeriksaSelanjutnya.text = ": ${itemData?.periksaSelanjutnya}"
        binding.tvSaran.text = ": ${itemData?.nasihat}"
        binding.tvTanggal.text = itemData?.tanggalPeriksa
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return root
    }
}