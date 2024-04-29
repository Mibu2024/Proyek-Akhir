package com.proyekakhir.mibu.user.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.BidanHomeViewModel
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.adapter.ListIbuAdapter
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.databinding.FragmentChatBidanBinding
import com.proyekakhir.mibu.databinding.FragmentHomeBinding
import com.proyekakhir.mibu.user.ui.home.model.BidanData

class ChatBidanFragment : DialogFragment() {
    private var _binding: FragmentChatBidanBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(requireActivity(), factory).get(HomeViewModel::class.java)

        _binding = FragmentChatBidanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val bidanArrayList = arrayListOf<BidanData>()
        val adapter = ListBidanAdapter(bidanArrayList)
        val rvBidan = binding.rvBidan
        rvBidan.layoutManager = LinearLayoutManager(requireContext())
        rvBidan.setHasFixedSize(true)
        rvBidan.adapter = adapter

        viewModel.dataList.observe(viewLifecycleOwner) { data ->
            adapter.setData(data)
            if (data.isEmpty()){
                binding.noDataBidan.visibility = View.VISIBLE
            } else {
                binding.noDataBidan.visibility = View.GONE
            }
        }

        return root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

}