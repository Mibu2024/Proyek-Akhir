package com.proyekakhir.mibu.user.ui.settings

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.FragmentAboutAppBinding
import com.proyekakhir.mibu.databinding.FragmentAboutBinding
import com.proyekakhir.mibu.databinding.FragmentSettingsBinding

class AboutFragment : Fragment() {
    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }

        return root
    }

}