package com.proyekakhir.mibu.user.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.databinding.FragmentDataPribadiBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.home.HomeViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class DataPribadiFragment : Fragment() {
    private var _binding: FragmentDataPribadiBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataPribadiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.edEmail.isEnabled = false
        binding.edNamaSuami.isEnabled = false
        binding.edKelahiranke.isEnabled = false
        binding.edTelpon.isEnabled = false
        binding.edFullname.isEnabled = false
        binding.edAlamat.isEnabled = false
        binding.btnSimpan.visibility = View.GONE
        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }


        viewModel.ibu.observe(viewLifecycleOwner, { response ->
            lifecycleScope.launch {
                val dataStore: DataStore<Preferences> = requireContext().dataStore
                val userPreference = UserPreference.getInstance(dataStore)
                val userId = userPreference.getSession().firstOrNull()?.id ?: 0

                // Filter the list based on the user ID
                val filteredList =
                    response.dataIbuHamils?.filter { it?.id == userId } ?: emptyList()

                // Check if the filtered list has any items
                if (filteredList.isNotEmpty()) {
                    // Assuming there's only one item in the filtered list
                    val ibuHamil = filteredList[0]
                    binding.edEmail.setText(ibuHamil?.email)
                    binding.edFullname.setText(ibuHamil?.namaIbu)
                    binding.edAlamat.setText(ibuHamil?.alamat)
                    binding.edNamaSuami.setText(ibuHamil?.namaSuami)
                    binding.edTelpon.setText(ibuHamil?.noTelepon)
                    binding.edKelahiranke.setText(ibuHamil?.kehamilanKe.toString())
                }
            }
        })

        return root
    }
}