package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.ParentRvAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.AddCatatanKesehatanFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.AddCatatanNifasFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.AddDataAnakFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ChildItem
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ParentItem
import com.proyekakhir.mibu.databinding.FragmentDetailIbuBinding

class DetailIbuFragment : Fragment() {
    private var _binding: FragmentDetailIbuBinding? = null
    private val binding get() = _binding!!
    private lateinit var parentRv: RecyclerView
    private lateinit var parentList: ArrayList<ParentItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailIbuBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val itemData = arguments?.getSerializable("itemData") as IbuHamilData

        binding.tvNamaIbu.text = itemData.fullname
        binding.tvUmurIbu.text = itemData.umur
        binding.tvNoTelponIbu.text = itemData.noTelepon

        parentRv = binding.parentRecyclerView
        parentRv.setHasFixedSize(true)
        parentRv.layoutManager = LinearLayoutManager(requireContext())
        parentList = ArrayList()

        prepareData()
        val adapter = ParentRvAdapter(parentList)
        parentRv.adapter = adapter

        binding.btnAddCatatanKesehatan.setOnClickListener {
            val addCatatanKesehatan = AddCatatanKesehatanFragment()
            val bundle = Bundle()
            bundle.putSerializable("itemData", itemData)
            addCatatanKesehatan.arguments = bundle

            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment_activity_bidan_main, addCatatanKesehatan)
                .addToBackStack(null) // Add this transaction to the back stack
                .commit()
        }

        binding.btnAddCatatanNifas.setOnClickListener {
            val addCatatanNifas = AddCatatanNifasFragment()
            val bundle = Bundle()
            bundle.putSerializable("itemData", itemData)
            addCatatanNifas.arguments = bundle

            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment_activity_bidan_main, addCatatanNifas)
                .addToBackStack(null) // Add this transaction to the back stack
                .commit()
        }

        binding.btnAddAnak.setOnClickListener {
            val addDataAnak = AddDataAnakFragment()
            val bundle = Bundle()
            bundle.putSerializable("itemData", itemData)
            addDataAnak.arguments = bundle

            val fragmentManager = (context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment_activity_bidan_main, addDataAnak)
                .addToBackStack(null) // Add this transaction to the back stack
                .commit()
        }

        return root
    }

    private fun prepareData(){

        val childItems1 = ArrayList<ChildItem>()
        childItems1.add(ChildItem("Sabtu", "12-09-2024"))
        childItems1.add(ChildItem("Sabtu", "12-09-2024"))
        childItems1.add(ChildItem("Sabtu", "12-09-2024"))

        val childItems2 = ArrayList<ChildItem>()
        childItems2.add(ChildItem("Sabtu", "12-09-2024"))
        childItems2.add(ChildItem("Sabtu", "12-09-2024"))
        childItems2.add(ChildItem("Sabtu", "12-09-2024"))

        parentList.add(ParentItem("History Catatan Kesehatan", childItems1))
        parentList.add(ParentItem("History Catatan Nifas", childItems2))

    }
}