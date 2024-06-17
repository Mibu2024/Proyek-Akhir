package com.proyekakhir.mibu.user.ui.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentArtikelBinding
import com.proyekakhir.mibu.user.api.response.DataArtikelItem
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.home.HomeViewModel

class ArtikelFragment : Fragment() {
    private var _binding: FragmentArtikelBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListArtikelAdapter
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentArtikelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adapter = ListArtikelAdapter(listOf())
        val rvArtikel = binding.rvArtikel
        rvArtikel.layoutManager = LinearLayoutManager(requireContext())
        rvArtikel.setHasFixedSize(true)
        rvArtikel.adapter = adapter

        viewModel.artikel.observe(viewLifecycleOwner, { response ->
            val list = response.dataArtikel
            if (list != null) {
                adapter.listArtikel = list
                adapter.notifyDataSetChanged()
                binding.noDataHome.visibility = View.GONE
            } else {
                binding.noDataHome.visibility = View.VISIBLE
            }
        })

        adapter.listener = object : ListArtikelAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: DataArtikelItem) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_artikelFragment_to_detailArtikelFragment2,
                    bundle
                )
            }
        }

        return root
    }
}