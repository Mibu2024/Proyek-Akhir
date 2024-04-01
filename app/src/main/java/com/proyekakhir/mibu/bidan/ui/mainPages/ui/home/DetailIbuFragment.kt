package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.ParentRvAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ChildItem
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.ParentItem
import com.proyekakhir.mibu.databinding.FragmentBidanHomeBinding
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

        parentRv = binding.parentRecyclerView
        parentRv.setHasFixedSize(true)
        parentRv.layoutManager = LinearLayoutManager(requireContext())
        parentList = ArrayList()

        prepareData()
        val adapter = ParentRvAdapter(parentList)
        parentRv.adapter = adapter

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