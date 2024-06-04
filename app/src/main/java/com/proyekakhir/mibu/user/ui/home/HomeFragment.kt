package com.proyekakhir.mibu.user.ui.home

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
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.network.NetworkConnection
import com.proyekakhir.mibu.databinding.FragmentHomeBinding
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
//    private lateinit var homeViewModel: HomeViewModel
    private lateinit var adapter: ListArtikelHomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getHplDate()

//        val repository = FirebaseRepository()
//        val factory = ViewModelFactory(repository)
//        homeViewModel = ViewModelProvider(requireActivity(), factory).get(HomeViewModel::class.java)

        val username = FirebaseAuth.getInstance().currentUser?.displayName
        binding.tvUsername.text = username

//        homeViewModel.fetchUserData()
//        homeViewModel.userData.observe(viewLifecycleOwner, Observer { data ->
//            if (!data?.profileImage.isNullOrEmpty()) {
//                Glide.with(requireActivity())
//                    .load(data?.profileImage)
//                    .into(binding.ivAvatar)
//            } else {
//                binding.ivAvatar.setImageResource(R.drawable.avatar)
//            }
//        })

        val listArtikel = arrayListOf<ArtikelModel>()
        adapter = ListArtikelHomeAdapter(listArtikel)
        val rvArtikel = binding.rvArtikelHome
        rvArtikel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvArtikel.setHasFixedSize(true)
        rvArtikel.adapter = adapter

//        homeViewModel.getArtikelByUser(
//            onDataChange = { list ->
//                // Update your adapter with the new list
//                adapter.setData(list)
//                if (list.isEmpty()){
//                    binding.noDataHome.visibility = View.VISIBLE
//                } else {
//                    binding.noDataHome.visibility = View.GONE
//                }
//            },
//            onCancelled = { error ->
//                // Handle the error
//            }
//        )

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
//        homeViewModel.isLoading.observe(requireActivity(), Observer { isLoading ->
//            if (isLoading) {
//                progressBar.visibility = View.VISIBLE
//            } else {
//                progressBar.visibility = View.GONE
//            }
//        })

        binding.fabHubungiBidan.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_chatBidanFragment)
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
                    binding.textView4.visibility = View.VISIBLE
                    binding.cardView.visibility = View.VISIBLE
                    binding.fabHubungiBidan.visibility = View.VISIBLE
                } else {
                    Toast.makeText(requireContext(), "Not Connected", Toast.LENGTH_SHORT).show()
                    binding.ivConnection.visibility = View.VISIBLE
                    binding.tvConnection.visibility = View.VISIBLE
                    binding.textView4.visibility = View.GONE
                    binding.cardView.visibility = View.GONE
                    binding.pbArtikelHome.visibility = View.GONE
                    binding.fabHubungiBidan.visibility = View.GONE

                }
            }
        }
    }

    private fun getHplDate() {
        // Initialize Firebase Auth and Firestore
        val auth = FirebaseAuth.getInstance()
        val firestore = FirebaseFirestore.getInstance()

        // Retrieve LMP date from Firestore
        val uid = auth.currentUser?.uid
        if (uid != null) {
            firestore.collection("users").document(uid).get()
                .addOnSuccessListener { document ->
                    if (document != null && document.exists()) {
                        val lmpDateString = document.getString("hpl_date")
                        if (!lmpDateString.isNullOrEmpty()) {
                            calculateAndDisplayCountdown(lmpDateString)
                        } else {
                            binding.tvHpl.text = "No LMP date set."
                        }
                    } else {
                        binding.tvHpl.text = "User data not found."
                    }
                }
                .addOnFailureListener { exception ->
                    binding.tvHpl.text = "Failed to load LMP date: ${exception.message}"
                }
        } else {
            binding.tvHpl.text = "User not logged in."
        }
    }

    private fun calculateAndDisplayCountdown(edbDateString: String) {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) // Ensure this matches your date format
        try {
            val edbDate = sdf.parse(edbDateString)
            if (edbDate != null) {
                val currentDate = Date()
                val diff = edbDate.time - currentDate.time
                val daysDiff = (diff / (1000 * 60 * 60 * 24)).toInt()

                // Ensure the daysDiff is not negative
                if (daysDiff >= 0) {
                    binding.tvHpl.text = "Perkiraan Kelahiran dalam: $daysDiff Hari"
                } else {
                    binding.tvHpl.text = "The due date has passed."
                }
            } else {
                binding.tvHpl.text = "Invalid EDB date format."
            }
        } catch (e: Exception) {
            binding.tvHpl.text = "Error parsing EDB date."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}