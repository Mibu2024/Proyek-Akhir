package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.databinding.FragmentBidanHomeBinding

class BidanHomeFragment : Fragment() {

    private var _binding: FragmentBidanHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bidanHomeViewModel =
            ViewModelProvider(this).get(BidanHomeViewModel::class.java)

        _binding = FragmentBidanHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val firebaseUsername = FirebaseAuth.getInstance().currentUser?.displayName
        binding.tvUsername.text = firebaseUsername


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}