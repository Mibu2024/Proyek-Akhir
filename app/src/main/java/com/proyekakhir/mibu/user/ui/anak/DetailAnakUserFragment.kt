package com.proyekakhir.mibu.user.ui.anak

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentDetailAnakUserBinding
import com.proyekakhir.mibu.user.api.response.DataAnaksItem
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import kotlinx.coroutines.launch

class DetailAnakUserFragment : Fragment() {
    private var _binding: FragmentDetailAnakUserBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatatanAnakViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

        viewModel.imunisasi.observe(viewLifecycleOwner) { response ->
            lifecycleScope.launch {
                val filteredList =
                    response.dataImunisasis?.filter { it?.idAnak == itemData.id } ?: emptyList()
                val sortedList = filteredList.sortedByDescending { it?.tanggal }

                // Immunization values
                val immunizationMap = mapOf(
                    binding.tvHepatitisB to sortedList.find { it?.hepatitisB != null }?.hepatitisB,
                    binding.tvBcg to sortedList.find { it?.bcg != null }?.bcg,
                    binding.tvPolioTetes1 to sortedList.find { it?.polioTetes1 != null }?.polioTetes1,
                    binding.tvDptHbHib1 to sortedList.find { it?.dptHbHib1 != null }?.dptHbHib1,
                    binding.tvPolioTetes2 to sortedList.find { it?.polioTetes2 != null }?.polioTetes2,
                    binding.tvRotavirus1 to sortedList.find { it?.rotaVirus1 != null }?.rotaVirus1,
                    binding.tvPcv1 to sortedList.find { it?.pcv1 != null }?.pcv1,
                    binding.tvDptHbHib2 to sortedList.find { it?.dptHbHib2 != null }?.dptHbHib2,
                    binding.tvPolioTetes3 to sortedList.find { it?.polioTetes3 != null }?.polioTetes3,
                    binding.tvRotavirus2 to sortedList.find { it?.rotaVirus2 != null }?.rotaVirus2,
                    binding.tvPcv2 to sortedList.find { it?.pcv2 != null }?.pcv2,
                    binding.tvDptHbHib3 to sortedList.find { it?.dptHbHib3 != null }?.dptHbHib3,
                    binding.tvPolioTetes4 to sortedList.find { it?.polioTetes4 != null }?.polioTetes4,
                    binding.tvPolioSuntik1 to sortedList.find { it?.polioSuntik1 != null }?.polioSuntik1,
                    binding.tvRotavirus3 to sortedList.find { it?.rotaVirus3 != null }?.rotaVirus3,
                    binding.tvCampakRubella to sortedList.find { it?.campakRubella != null }?.campakRubella,
                    binding.tvPolioSuntik2 to sortedList.find { it?.polioSuntik2 != null }?.polioSuntik2,
                    binding.tvJapaneseEncephalitis to sortedList.find { it?.japaneseEncephalitis != null }?.japaneseEncephalitis,
                    binding.tvPcv3 to sortedList.find { it?.pcv3 != null }?.pcv3,
                    binding.tvDptHbHibLanjutan to sortedList.find { it?.dptHbHibLanjutan != null }?.dptHbHibLanjutan,
                    binding.tvCampakRubellaLanjutan to sortedList.find { it?.campakRubellaLanjutan != null }?.campakRubellaLanjutan
                )

                immunizationMap.forEach { (textView, status) ->
                    when (status) {
                        "Sudah" -> {
                            textView.setBackgroundResource(R.drawable.bg_filled_green)
                            textView.setTextColor(Color.WHITE)
                            textView.text = "Sudah"
                            textView.setPadding(32, 32, 32, 32)
                        }

                        "Belum" -> {
                            textView.setBackgroundResource(R.drawable.bg_filled_red)
                            textView.setTextColor(Color.WHITE)
                            textView.text = "Belum"
                            textView.setPadding(32, 32, 32, 32)
                        }
                    }
                }

                val namaPemeriksa = sortedList.find { it?.namaPemeriksa != null }?.namaPemeriksa
                binding.tvNamaPemeriksa.text = namaPemeriksa
            }
        }

        return root
    }

}