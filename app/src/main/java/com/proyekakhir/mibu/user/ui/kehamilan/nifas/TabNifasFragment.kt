package com.proyekakhir.mibu.user.ui.kehamilan.nifas

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
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentTabNifasBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.api.response.DataNifasItem
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.kehamilan.CatatanKehamilanViewModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class TabNifasFragment : Fragment() {

    private var _binding: FragmentTabNifasBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatatanKehamilanViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabNifasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val list = arrayListOf<NifasModel>()
        val adapter = ListNifasAdapter(listOf())
        val rvNifas = binding.rvTabNifas
        rvNifas.layoutManager = LinearLayoutManager(requireContext())
        rvNifas.setHasFixedSize(true)
        rvNifas.adapter = adapter

        viewModel.nifas.observe(viewLifecycleOwner, Observer { response ->
            lifecycleScope.launch {
                val dataStore: DataStore<Preferences> = requireContext().dataStore
                val userPreference = UserPreference.getInstance(dataStore)
                val userId = userPreference.getSession().firstOrNull()?.id ?: 0
                Log.d("useridget", userId.toString())
                val filteredList = response.dataNifas?.filter { it?.idIbu == userId } ?: emptyList()
                adapter.list = filteredList
                if (filteredList.isEmpty()) {
                    binding.tvNoDataNifas.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataNifas.visibility = View.GONE
                }
                adapter.notifyDataSetChanged()
            }
        })

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        adapter.listener = object : ListNifasAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: DataNifasItem) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_catatan_kehamilan_to_detailNifasFragment2,
                    bundle
                )
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}