package com.proyekakhir.mibu.user.ui.anak

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentDetailAnakUserBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.api.response.DataAnaksItem
import com.proyekakhir.mibu.user.api.response.DataImunisasisItem
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.anak.model.AnakModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DetailAnakUserFragment : Fragment() {
    private var _binding: FragmentDetailAnakUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatatanAnakViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    private lateinit var dataAnak: List<DataImunisasisItem?>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailAnakUserBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val itemData = arguments?.getSerializable("itemData") as DataAnaksItem

        binding.tvNamaAnak.text = ": ${itemData.namaAnak}"
        binding.tvTanggalLahir.text = ": ${itemData.tanggalLahir}"
        binding.tvUmurBayi.text = ": ${itemData.umur}"
        binding.tvBeratBayi.text = ": ${itemData.beratBadan}"
        binding.tvNamaIbu.text = ": ${itemData.namaIbu}"

        viewModel.imunisasi.observe(viewLifecycleOwner, Observer { response ->
            lifecycleScope.launch {
                val filteredList = response.dataImunisasis?.filter { it?.idAnak == itemData.id } ?: emptyList()
                val sortedList = filteredList.sortedByDescending { it?.tanggal }

                //value imunisasi
                val polio2 = sortedList.find { it?.imunisasiDptHbHib1Polio2 != null }?.imunisasiDptHbHib1Polio2
                val polio3 = sortedList.find { it?.imunisasiDptHbHib2Polio3 != null }?.imunisasiDptHbHib2Polio3
                val polio4 = sortedList.find { it?.imunisasiDptHbHib3Polio4 != null }?.imunisasiDptHbHib3Polio4
                val campak = sortedList.find { it?.imunisasiCampak != null }?.imunisasiCampak
                val hib1Dosis = sortedList.find { it?.imunisasiDptHbHib1Dosis != null }?.imunisasiDptHbHib1Dosis
                val rubella1Dosis = sortedList.find { it?.imunisasiCampakRubella1Dosis != null }?.imunisasiCampakRubella1Dosis
                val rubellaDt = sortedList.find { it?.imunisasiCampakRubellaDanDt != null }?.imunisasiCampakRubellaDanDt
                val tetanus = sortedList.find { it?.imunisasiTetanusDiphteriaTd != null }?.imunisasiTetanusDiphteriaTd
                val namaPemeriksa = sortedList.find { it?.namaPemeriksa != null }?.namaPemeriksa

                binding.tvNamaPemeriksa.setText(namaPemeriksa)
                binding.tvPolio2.setText(polio2)
                binding.tvPolio3.setText(polio3)
                binding.tvPolio4.setText(polio4)
                binding.tvCampak.setText(campak)
                binding.tvHib1.setText(hib1Dosis)
                binding.tvRubella1.setText(rubella1Dosis)
                binding.tvRubellaDt.setText(rubellaDt)
                binding.tvTetanus.setText(tetanus)

                when (polio2) {
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

                when (polio3) {
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

                when (polio4) {
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

                when (campak) {
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

                when (hib1Dosis) {
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

                when (rubella1Dosis) {
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

                when (rubellaDt) {
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

                when (tetanus) {
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


            }
        })

        return root
    }

}