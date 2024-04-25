package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.databinding.FragmentBidanArtikelBinding

class BidanArtikelFragment : Fragment() {

    private var _binding: FragmentBidanArtikelBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        val bidanArtikelViewModel = ViewModelProvider(this,factory).get(BidanArtikelViewModel::class.java)

        _binding = FragmentBidanArtikelBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.layoutAddArtikel.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_artikel_to_addArtikelFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}