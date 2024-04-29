package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.ListIbuAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.BidanSettingsViewModel
import com.proyekakhir.mibu.bidan.ui.network.NetworkConnection
import com.proyekakhir.mibu.databinding.FragmentBidanHomeBinding
import java.util.Locale

class BidanHomeFragment : Fragment() {

    private var _binding: FragmentBidanHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var bidanHomeViewModel: BidanHomeViewModel
    private lateinit var rvIbu: RecyclerView
    private lateinit var ibuArrayList : ArrayList<IbuHamilData>
    private lateinit var adapter: ListIbuAdapter
    private lateinit var settingsViewModel: BidanSettingsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        bidanHomeViewModel = ViewModelProvider(requireActivity(), factory).get(BidanHomeViewModel::class.java)
        settingsViewModel = ViewModelProvider(requireActivity(), factory).get(BidanSettingsViewModel::class.java)

        _binding = FragmentBidanHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val firebaseUsername = FirebaseAuth.getInstance().currentUser?.displayName
        binding.tvUsername.text = firebaseUsername

        settingsViewModel.fetchUserData()
        settingsViewModel.userData.observe(viewLifecycleOwner, Observer { data ->
            if (!data?.profileImage.isNullOrEmpty()) {
                Glide.with(requireActivity())
                    .load(data?.profileImage)
                    .into(binding.ivAvatar)
            } else {
                binding.ivAvatar.setImageResource(R.drawable.avatar)
            }
        })

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

        adapter.listener = object : ListIbuAdapter.OnItemClickListener {
            override fun onItemClick(item: IbuHamilData) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(R.id.action_navigation_home_to_detailIbuFragment, bundle)
            }
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //check connection
        val networkConnection = NetworkConnection(requireContext())
        networkConnection.observe(viewLifecycleOwner) { isConnected ->
            if (isAdded) { // Check if the fragment is added
                if (isConnected) {
                    binding.ivConnection.visibility = View.GONE
                    binding.tvConnection.visibility = View.GONE
                    binding.rvIbuHamil.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Not Connected", Toast.LENGTH_SHORT).show()
                    binding.ivConnection.visibility = View.VISIBLE
                    binding.tvConnection.visibility = View.VISIBLE
                    binding.pbHome.visibility = View.GONE
                    binding.rvIbuHamil.visibility = View.GONE
                }
            }
        }
    }


    private fun searchList(query: String?){
        if (query != null){
            val searchedList = ArrayList<IbuHamilData>()
            for (i in ibuArrayList){
                if (i.fullname?.lowercase(Locale.ROOT)?.contains(query) == true
                    || i.nik?.lowercase(Locale.ROOT)?.contains(query) == true ){
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