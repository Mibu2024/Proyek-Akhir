package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.ListIbuAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.databinding.FragmentBidanArtikelBinding

class BidanArtikelFragment : Fragment() {

    private var _binding: FragmentBidanArtikelBinding? = null
    private val binding get() = _binding!!
    private lateinit var listArtikel: ArrayList<ArtikelData>
    private lateinit var adapter: ListArtikelAdapter
    private lateinit var rvArtikel: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        val bidanArtikelViewModel = ViewModelProvider(this,factory).get(BidanArtikelViewModel::class.java)

        _binding = FragmentBidanArtikelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.layoutAddArtikel.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_artikel_to_addArtikelFragment)
        }

        listArtikel = arrayListOf<ArtikelData>()
        adapter = ListArtikelAdapter(listArtikel)
        rvArtikel = binding.rvArtikel
        rvArtikel.layoutManager = LinearLayoutManager(requireContext())
        rvArtikel.setHasFixedSize(true)
        rvArtikel.adapter = adapter

        bidanArtikelViewModel.getArtikelByUser(
            onDataChange = { list ->
                // Update your adapter with the new list
                adapter.setData(list)
            },
            onCancelled = { error ->
                // Handle the error
            }
        )

        adapter.listener = object : ListArtikelAdapter.OnItemClickListener {
            override fun onItemClick(item: ArtikelData) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(R.id.action_navigation_artikel_to_detailArtikelFragment, bundle)
            }
        }

        val progressBar = binding.pbArtikel
        bidanArtikelViewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}