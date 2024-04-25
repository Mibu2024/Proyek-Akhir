package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.adapters.SearchViewBindingAdapter.setOnQueryTextListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.ListIbuAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.databinding.FragmentBidanHomeBinding
import java.util.Locale

class BidanHomeFragment : Fragment() {

    private var _binding: FragmentBidanHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var bidanHomeViewModel: BidanHomeViewModel
    private lateinit var rvIbu: RecyclerView
    private lateinit var ibuArrayList : ArrayList<IbuHamilData>
    private lateinit var adapter: ListIbuAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)

        bidanHomeViewModel = ViewModelProvider(requireActivity(), factory).get(BidanHomeViewModel::class.java)

        _binding = FragmentBidanHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val firebaseUsername = FirebaseAuth.getInstance().currentUser?.displayName
        binding.tvUsername.text = firebaseUsername

        ibuArrayList = arrayListOf<IbuHamilData>()
        adapter = ListIbuAdapter(ibuArrayList)
        rvIbu = binding.rvIbuHamil
        rvIbu.layoutManager = LinearLayoutManager(requireContext())
        rvIbu.setHasFixedSize(true)
        rvIbu.adapter = adapter

        bidanHomeViewModel.dataList.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
        }

        val progressBar = binding.pbHome
        bidanHomeViewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        binding.svBidanHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList(newText)
                return true
            }

        })

        return root
    }

    private fun searchList(query: String?){
        if (query != null){
            val searchedList = ArrayList<IbuHamilData>()
            for (i in ibuArrayList){
                if (i.fullname?.lowercase(Locale.ROOT)?.contains(query) == true){
                    searchedList.add(i)
                }
            }
            adapter.setFilteredList(searchedList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}