package com.proyekakhir.mibu.user.ui.kehamilan.kb

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentTabKbBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.api.response.DataLayananKbsItem
import com.proyekakhir.mibu.user.api.response.KbResponse
import com.proyekakhir.mibu.user.auth.viewmodel.LoginViewModel
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.kehamilan.CatatanKehamilanViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class TabKbFragment : Fragment() {
    private var _binding: FragmentTabKbBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatatanKehamilanViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabKbBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val adapter = ListKbAdapter(listOf())
        val rvKesehatan = binding.rvTabKb
        rvKesehatan.layoutManager = LinearLayoutManager(requireContext())
        rvKesehatan.setHasFixedSize(true)
        rvKesehatan.adapter = adapter

        viewModel.kb.observe(viewLifecycleOwner, { response ->
            updateRecyclerView(response)
        })

        loginViewModel.loginResult.observe(viewLifecycleOwner, { loginResponse ->
            // When login result is updated, trigger data fetch
            viewModel.getCatatanKb()
        })

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(requireActivity(), { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        adapter.listener = object : ListKbAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: DataLayananKbsItem) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_catatan_kehamilan_navbar_to_detailKbFragment,
                    bundle
                )
            }
        }

        return root
    }

    private fun updateRecyclerView(response: KbResponse) {
        lifecycleScope.launch {
            val dataStore: DataStore<Preferences> = requireContext().dataStore
            val userPreference = UserPreference.getInstance(dataStore)
            val userId = userPreference.getSession().firstOrNull()?.id ?: 0
            val filteredList = response.dataLayananKbs?.filter { it?.idIbu == userId } ?: emptyList()
            (binding.rvTabKb.adapter as ListKbAdapter).apply {
                listKb = filteredList
                notifyDataSetChanged()
            }
            binding.tvNoDataKb.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
        }
    }

}