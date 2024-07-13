package com.proyekakhir.mibu.user.ui.kehamilan.kesehatan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.proyekakhir.mibu.databinding.FragmentDetailKesehatanBinding
import com.proyekakhir.mibu.user.api.response.DataKesehatanItem

class DetailKesehatanFragment : Fragment() {
    private var _binding: FragmentDetailKesehatanBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailKesehatanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as? DataKesehatanItem

        binding.tvTekananDarah.text = "${itemData?.tekananDarah} mmHg"
        binding.tvBeratBadan.text = "${itemData?.beratBadan} Kg"
        binding.tvUmurKehamilan.text = "${itemData?.umurKehamilan} Minggu"
        binding.tvTinggiFundus.text = "${itemData?.tinggiFundus} Cm"
        binding.tvLetakJanin.text = "${itemData?.letakJanin}"
        binding.tvDenyutJanin.text = "${itemData?.denyutJantungJanin} Bpm"
        binding.tvKakiBengkakValue.text = "${itemData?.kakiBengkak}"
        binding.tvTindakanValue.text = "${itemData?.hasilLab}"
        binding.tvHasilLabValue.text = "${itemData?.tindakan}"
        binding.tvKeluhanValue.text = "${itemData?.keluhan}"
        binding.tvNamaPemeriksaValue.text = "${itemData?.namaPemeriksa}"
        binding.tvTinggiBadanValue.text = "${itemData?.tinggiBadan}"
        binding.tvLingkarPerutValue.text = "${itemData?.lingkarPerut}"
        binding.tvLingkarLenganValue.text = "${itemData?.lingkarLenganAtas}"
//        binding.tvTempatPeriksaValue.text = ": ${itemData?.tempatPeriksa}"
//        binding.tvTglPeriksaSelanjutnya.text = ": ${itemData?.periksaSelanjutnya}"
        binding.tvSaran.text = "${itemData?.nasihat}"
        binding.tvTanggal.text = itemData?.tanggal
        binding.ivBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        if (!itemData?.fotoUsg.isNullOrEmpty()) {
            Glide.with(this)
                .load(itemData?.fotoUsg)
                .into(binding.ivUsg)
        } else {
            binding.ivUsg.visibility = View.GONE
            binding.textView44.visibility = View.GONE
        }

        return root
    }
}