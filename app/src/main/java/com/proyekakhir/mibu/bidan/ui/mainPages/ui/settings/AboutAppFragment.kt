package com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.databinding.FragmentAboutAppBinding

class AboutAppFragment : Fragment() {
    private var _binding: FragmentAboutAppBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutAppBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return root
    }

}