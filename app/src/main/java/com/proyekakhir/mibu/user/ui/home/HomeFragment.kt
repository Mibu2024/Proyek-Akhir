package com.proyekakhir.mibu.user.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentHomeBinding
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: ListArtikelHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        homeViewModel = ViewModelProvider(requireActivity(), factory).get(HomeViewModel::class.java)

        val username = FirebaseAuth.getInstance().currentUser?.displayName
        binding.tvUsername.text = username

        homeViewModel.fetchUserData()
        homeViewModel.userData.observe(viewLifecycleOwner, Observer { data ->
            if (!data?.profileImage.isNullOrEmpty()) {
                Glide.with(requireActivity())
                    .load(data?.profileImage)
                    .into(binding.ivAvatar)
            } else {
                binding.ivAvatar.setImageResource(R.drawable.avatar)
            }
        })

        val listArtikel = arrayListOf<ArtikelModel>()
        adapter = ListArtikelHomeAdapter(listArtikel)
        val rvArtikel = binding.rvArtikelHome
        rvArtikel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvArtikel.setHasFixedSize(true)
        rvArtikel.adapter = adapter

        homeViewModel.getArtikelByUser(
            onDataChange = { list ->
                // Update your adapter with the new list
                adapter.setData(list)
            },
            onCancelled = { error ->
                // Handle the error
            }
        )

        adapter.listener = object : ListArtikelHomeAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: ArtikelModel) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_home_to_detailArtikelFragment2,
                    bundle
                )
            }
        }

        val progressBar = binding.pbArtikelHome
        homeViewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
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