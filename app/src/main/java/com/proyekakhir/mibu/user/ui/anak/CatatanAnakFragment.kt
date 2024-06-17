package com.proyekakhir.mibu.user.ui.anak

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentCatatanAnakBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.api.response.DataAnaksItem
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.NetworkConnection
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch

class CatatanAnakFragment : Fragment() {
    private var _binding: FragmentCatatanAnakBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<CatatanAnakViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatatanAnakBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = ListAnakAdapter(listOf())
        val rvAnak = binding.rvDataAnak
        rvAnak.layoutManager = LinearLayoutManager(requireContext())
        rvAnak.setHasFixedSize(true)
        rvAnak.adapter = adapter

        viewModel.anak.observe(viewLifecycleOwner, { response ->
            lifecycleScope.launch {
                val dataStore: DataStore<Preferences> = requireContext().dataStore
                val userPreference = UserPreference.getInstance(dataStore)
                val userId = userPreference.getSession().firstOrNull()?.id ?: 0
                val filteredList = response.dataAnaks?.filter { it?.idIbu == userId } ?: emptyList()
                adapter.list = filteredList
                if (filteredList.isEmpty()) {
                    binding.tvNoDataAnak.visibility = View.VISIBLE
                } else {
                    binding.tvNoDataAnak.visibility = View.GONE
                }
                adapter.notifyDataSetChanged()
            }
        })

        val progressBar = binding.progressBar
        viewModel.isLoading.observe(requireActivity(), { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        adapter.listener = object : ListAnakAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: DataAnaksItem) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_catatan_anak_to_detailAnakUserFragment,
                    bundle
                )
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check connection
        val networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(requireActivity()) {
            if (isAdded) {
                if (it) {
                    binding.ivConnection.visibility = View.GONE
                    binding.tvConnection.visibility = View.GONE
                    binding.rvDataAnak.visibility = View.VISIBLE
                    binding.textView5.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Not Connected", Toast.LENGTH_SHORT).show()
                    binding.ivConnection.visibility = View.VISIBLE
                    binding.tvConnection.visibility = View.VISIBLE
                    binding.rvDataAnak.visibility = View.GONE
                    binding.textView5.visibility = View.GONE
                    binding.tvNoDataAnak.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE

                }
            }
        }
    }

}