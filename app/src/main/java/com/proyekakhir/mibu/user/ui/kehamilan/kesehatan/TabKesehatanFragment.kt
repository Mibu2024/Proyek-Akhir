package com.proyekakhir.mibu.user.ui.kehamilan.kesehatan

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
import com.proyekakhir.mibu.databinding.FragmentTabKesehatanBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.api.response.DataKesehatanItem
import com.proyekakhir.mibu.user.api.response.KesehatanResponse
import com.proyekakhir.mibu.user.auth.viewmodel.LoginViewModel
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.kehamilan.CatatanKehamilanViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class TabKesehatanFragment : Fragment() {
    private var _binding: FragmentTabKesehatanBinding? = null
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
        _binding = FragmentTabKesehatanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getCatatanKehamilan()
            refreshData()
            binding.swipeRefreshLayout.isRefreshing = false
        }

        val adapter = ListKesehatanAdapter(listOf())
        val rvKesehatan = binding.rvTabKesehatan
        rvKesehatan.layoutManager = LinearLayoutManager(requireContext())
        rvKesehatan.setHasFixedSize(true)
        rvKesehatan.adapter = adapter

        viewModel.kesehatan.observe(viewLifecycleOwner) { response ->
            updateRecyclerView(response)
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResponse ->
            // When login result is updated, trigger data fetch
            viewModel.getCatatanKehamilan()
        }

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        }

        adapter.listener = object : ListKesehatanAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: DataKesehatanItem) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_catatan_kehamilan_to_detailKesehatanFragment,
                    bundle
                )
            }
        }

        return root
    }

    private fun refreshData() {
        viewModel.kesehatan.observe(viewLifecycleOwner) { response ->
            updateRecyclerView(response)
        }
    }

    private fun updateRecyclerView(response: KesehatanResponse) {
        lifecycleScope.launch {
            val dataStore: DataStore<Preferences> = requireContext().dataStore
            val userPreference = UserPreference.getInstance(dataStore)
            val userId = userPreference.getSession().firstOrNull()?.id ?: 0
            val filteredList = response.dataKesehatan?.filter { it?.idIbu == userId } ?: emptyList()
            (binding.rvTabKesehatan.adapter as ListKesehatanAdapter).apply {
                list = filteredList
                notifyDataSetChanged()
            }
            binding.tvNoDataKesehatan.visibility =
                if (filteredList.isEmpty()) View.VISIBLE else View.GONE
        }
    }

}