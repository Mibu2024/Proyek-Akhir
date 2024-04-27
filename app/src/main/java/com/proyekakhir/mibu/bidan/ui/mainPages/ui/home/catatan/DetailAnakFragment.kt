package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.databinding.FragmentDetailAnakBinding
import com.proyekakhir.mibu.databinding.FragmentDetailKesehatanBinding

class DetailAnakFragment : Fragment() {
    private var _binding: FragmentDetailAnakBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAnakBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as AddDataAnak

        binding.tvNamaAnak.text = itemData.namaAnak
        binding.tvTanggalLahir.text = itemData.tanggalLahir
        binding.tvUmurBayi.text = itemData.umur
        binding.tvBeratBayi.text = itemData.beratBadan
        binding.tvNamaIbu.text = itemData.namaIbu

        when (itemData.dptHb1Polio2){
            "Sudah" -> {
                binding.tvPolio2.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvPolio2.setTextColor(Color.WHITE)
                binding.tvPolio2.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvPolio2.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvPolio2.setTextColor(Color.WHITE)
                binding.tvPolio2.setPadding(32, 32, 32, 32)
            }
        }

        when (itemData.dptHb2Polio3){
            "Sudah" -> {
                binding.tvPolio3.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvPolio3.setTextColor(Color.WHITE)
                binding.tvPolio3.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvPolio3.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvPolio3.setTextColor(Color.WHITE)
                binding.tvPolio3.setPadding(32, 32, 32, 32)
            }
        }

        when (itemData.dptHb3Polio4){
            "Sudah" -> {
                binding.tvPolio4.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvPolio4.setTextColor(Color.WHITE)
                binding.tvPolio4.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvPolio4.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvPolio4.setTextColor(Color.WHITE)
                binding.tvPolio4.setPadding(32, 32, 32, 32)
            }
        }

        when (itemData.campak){
            "Sudah" -> {
                binding.tvCampak.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvCampak.setTextColor(Color.WHITE)
                binding.tvCampak.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvCampak.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvCampak.setTextColor(Color.WHITE)
                binding.tvCampak.setPadding(32, 32, 32, 32)
            }
        }

        when (itemData.dptHb1Dosis){
            "Sudah" -> {
                binding.tvHib1.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvHib1.setTextColor(Color.WHITE)
                binding.tvHib1.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvHib1.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvHib1.setTextColor(Color.WHITE)
                binding.tvHib1.setPadding(32, 32, 32, 32)
            }
        }

        when (itemData.campakRubella1Dosis){
            "Sudah" -> {
                binding.tvRubella1.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvRubella1.setTextColor(Color.WHITE)
                binding.tvRubella1.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvRubella1.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvRubella1.setTextColor(Color.WHITE)
                binding.tvRubella1.setPadding(32, 32, 32, 32)
            }
        }

        when (itemData.campakRubellaDt){
            "Sudah" -> {
                binding.tvRubellaDt.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvRubellaDt.setTextColor(Color.WHITE)
                binding.tvRubellaDt.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvRubellaDt.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvRubellaDt.setTextColor(Color.WHITE)
                binding.tvRubellaDt.setPadding(32, 32, 32, 32)
            }
        }

        when (itemData.tetanus){
            "Sudah" -> {
                binding.tvTetanus.setBackgroundResource(R.drawable.bg_filled_green)
                binding.tvTetanus.setTextColor(Color.WHITE)
                binding.tvTetanus.setPadding(32, 32, 32, 32)
            }

            "Belum" -> {
                binding.tvTetanus.setBackgroundResource(R.drawable.bg_filled_red)
                binding.tvTetanus.setTextColor(Color.WHITE)
                binding.tvTetanus.setPadding(32, 32, 32, 32)
            }
        }

        binding.tvPolio2.text = itemData.dptHb1Polio2
        binding.tvPolio3.text = itemData.dptHb2Polio3
        binding.tvPolio4.text = itemData.dptHb3Polio4
        binding.tvCampak.text = itemData.campak
        binding.tvHib1.text = itemData.dptHb1Dosis
        binding.tvRubella1.text = itemData.campakRubella1Dosis
        binding.tvRubellaDt.text = itemData.campakRubellaDt
        binding.tvTetanus.text = itemData.tetanus

        binding.tvPeriksaSelanjutnya.text = itemData.periksaSelanjutnya

        return root
    }

}