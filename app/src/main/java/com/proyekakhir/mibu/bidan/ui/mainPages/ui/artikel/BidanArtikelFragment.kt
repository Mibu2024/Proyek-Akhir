package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.network.NetworkConnection
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
        val bidanArtikelViewModel =
            ViewModelProvider(this, factory).get(BidanArtikelViewModel::class.java)

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
                if (list.isEmpty()){
                    binding.tvNoData.visibility = View.VISIBLE
                } else {
                    binding.tvNoData.visibility = View.GONE
                }
            },
            onCancelled = { error ->
                // Handle the error
            }
        )

        adapter.listener = object : ListArtikelAdapter.OnItemClickListener {
            override fun onItemClick(item: ArtikelData) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_artikel_to_detailArtikelFragment,
                    bundle
                )
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check connection
        val networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(requireActivity()) {
            if (isAdded) {
                if (it) {
                    binding.ivConnection.visibility = View.GONE
                    binding.tvConnection.visibility = View.GONE
                    binding.rvArtikel.visibility = View.VISIBLE
                    binding.layoutAddArtikel.visibility = View.VISIBLE
                    binding.textView15.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Not Connected", Toast.LENGTH_SHORT).show()
                    binding.ivConnection.visibility = View.VISIBLE
                    binding.tvConnection.visibility = View.VISIBLE
                    binding.pbArtikel.visibility = View.GONE
                    binding.rvArtikel.visibility = View.GONE
                    binding.tvNoData.visibility = View.GONE
                    binding.layoutAddArtikel.visibility = View.GONE
                    binding.textView15.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}