package com.proyekakhir.mibu.user.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentHomeBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.api.response.DataArtikelItem
import com.proyekakhir.mibu.user.api.response.IbuResponse
import com.proyekakhir.mibu.user.auth.viewmodel.LoginViewModel
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.NetworkConnection
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListArtikelHomeAdapter
    private val viewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }

    private val loginViewModel: LoginViewModel by activityViewModels {
        ViewModelFactory.getInstance(requireContext())
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val swipeRefreshLayout = binding.swipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            refreshData()
        }

        lifecycleScope.launch {
            val dataStore: DataStore<Preferences> = requireContext().dataStore
            val userPreference = UserPreference.getInstance(dataStore)
            val name = userPreference.getSession().firstOrNull()?.name ?: 0
            binding.tvUsername.text = name.toString()
        }

        binding.catatanKehamilan.setOnClickListener {
            findNavController().navigate(R.id.navigation_catatan_kehamilan_navbar)
            activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE
        }

        binding.catatanAnak.setOnClickListener {
            findNavController().navigate(R.id.navigation_catatan_anak)
            activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE
        }

        binding.artikel.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_artikelFragment)
            activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE
        }

        binding.kb.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_home_to_tabKbFragment)
            activity?.findViewById<BottomNavigationView>(R.id.nav_view)?.visibility = View.GONE
        }

        adapter = ListArtikelHomeAdapter(listOf())
        val rvArtikel = binding.rvArtikelHome
        rvArtikel.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvArtikel.setHasFixedSize(true)
        rvArtikel.adapter = adapter

        viewModel.artikel.observe(viewLifecycleOwner) { response ->
            val list = response.dataArtikel
            if (list != null) {
                adapter.listArtikel = list
                adapter.notifyDataSetChanged()
                binding.noDataHome.visibility = View.GONE
            } else {
                binding.noDataHome.visibility = View.VISIBLE
            }

        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) { loginResponse ->
            // When login result is updated, trigger data fetch
            viewModel.getHpl()
            viewModel.getArtikel()
        }

        viewModel.hpl.observe(viewLifecycleOwner) { response ->
            getHplDate(response)
        }

        adapter.listener = object : ListArtikelHomeAdapter.OnItemClickListenerHome {
            override fun onItemClick(item: DataArtikelItem) {
                val bundle = Bundle()
                bundle.putSerializable("itemData", item)
                findNavController().navigate(
                    R.id.action_navigation_home_to_detailArtikelFragment2,
                    bundle
                )
            }
        }

        val progressBar = binding.pbArtikelHome
        viewModel.isLoading.observe(requireActivity()) { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
                binding.noDataHome.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
            }
        }

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

    private fun getHplDate(response: IbuResponse) {
        lifecycleScope.launch {
            val dataStore: DataStore<Preferences> = requireContext().dataStore
            val userPreference = UserPreference.getInstance(dataStore)
            val userId = userPreference.getSession().firstOrNull()?.id ?: 0


            // Filter the list based on the user ID
            val filteredList = response.dataIbuHamils?.filter { it?.id == userId } ?: emptyList()


            // Check if the filtered list has any items
            if (filteredList.isNotEmpty()) {
                // Assuming there's only one item in the filtered list
                val ibuHamil = filteredList[0]
                val tanggalHpl = ibuHamil?.tanggalHpl

                if (!tanggalHpl.isNullOrEmpty()) {
                    calculateAndDisplayCountdown(tanggalHpl)
                    binding.banner.setBackgroundResource(R.color.hpl)
                    binding.tvHpl.visibility = View.VISIBLE
                    binding.tvDescHpl.visibility = View.VISIBLE
                } else {
                    binding.banner.setBackgroundResource(R.drawable.artbannerhome)
                    binding.tvHpl.visibility = View.GONE
                    binding.tvDescHpl.visibility = View.GONE
                }
            }
        }
    }


    @SuppressLint("SetTextI18n")
    private fun calculateAndDisplayCountdown(edbDateString: String) {
        val sdf = SimpleDateFormat(
            "yyyy-MM-dd",
            Locale.getDefault()
        ) // Ensure this matches your date format
        try {
            val edbDate = sdf.parse(edbDateString)
            if (edbDate != null) {
                val currentDate = Date()
                val diff = edbDate.time - currentDate.time
                val daysDiff = (diff / (1000 * 60 * 60 * 24)).toInt()

                // Ensure the daysDiff is not negative
                if (daysDiff >= 0) {
                    binding.tvHpl.text = "$daysDiff Days"
                    binding.tvDescHpl.text = "Until your estimated day of birth on $edbDateString"
                } else {
                    binding.tvHpl.visibility = View.GONE
                    binding.tvDescHpl.visibility = View.GONE
                    binding.banner.setBackgroundResource(R.drawable.artbannerhome)
                }
            } else {
                binding.tvHpl.visibility = View.GONE
                binding.tvDescHpl.visibility = View.GONE
                binding.banner.setBackgroundResource(R.drawable.artbannerhome)
            }
        } catch (e: Exception) {
            binding.tvHpl.visibility = View.GONE
            binding.tvDescHpl.visibility = View.GONE
            binding.banner.setBackgroundResource(R.drawable.artbannerhome)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun refreshData() {
        // Logic to refresh data
        viewModel.getHpl()
        viewModel.artikel.observe(viewLifecycleOwner) { response ->
            val list = response.dataArtikel
            if (list != null) {
                adapter.listArtikel = list
                adapter.notifyDataSetChanged()
                binding.noDataHome.visibility = View.GONE
            } else {
                binding.noDataHome.visibility = View.VISIBLE
            }
            // Stop the refreshing animation once data is loaded
            binding.swipeRefreshLayout.isRefreshing = false
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner, { loginResponse ->
            viewModel.getHpl()
        })

        viewModel.hpl.observe(viewLifecycleOwner, { response ->
            getHplDate(response)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}