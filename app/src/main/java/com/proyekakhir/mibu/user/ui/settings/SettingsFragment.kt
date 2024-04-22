package com.proyekakhir.mibu.user.ui.settings

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.BidanLoginActivity
import com.proyekakhir.mibu.databinding.FragmentBidanSettingsBinding
import com.proyekakhir.mibu.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val username = FirebaseAuth.getInstance().currentUser?.displayName
        val email = FirebaseAuth.getInstance().currentUser?.email

        binding.username.text = username
        binding.email.text = email

        binding.itemLogout.setOnClickListener {
            logout()
        }

        return root
    }

    private fun logout() {
        FirebaseAuth.getInstance().signOut()
        val intent = Intent(requireContext(), BidanLoginActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }


}