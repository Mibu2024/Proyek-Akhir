package com.proyekakhir.mibu.user.ui.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.proyekakhir.mibu.databinding.FragmentChatBidanBinding
import com.proyekakhir.mibu.user.ui.home.model.BidanData

class ChatBidanFragment : DialogFragment() {
    private var _binding: FragmentChatBidanBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel> {
        com.proyekakhir.mibu.user.factory.ViewModelFactory.getInstance(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentChatBidanBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val adapter = ListBidanAdapter(listOf())
        val rvBidan = binding.rvBidan
        rvBidan.layoutManager = LinearLayoutManager(requireContext())
        rvBidan.setHasFixedSize(true)
        rvBidan.adapter = adapter

        viewModel.bidan.observe(viewLifecycleOwner, Observer { response ->
            val list = response.dataBidan
            if (list != null) {
                adapter.list = list
                adapter.notifyDataSetChanged()
                binding.noDataBidan.visibility = View.GONE
            } else {
                binding.noDataBidan.visibility = View.VISIBLE
            }

            Log.d("bidanapi", response.dataBidan.toString())
        })

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