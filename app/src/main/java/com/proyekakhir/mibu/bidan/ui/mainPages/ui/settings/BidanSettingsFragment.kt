package com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.bidan.ui.auth.BidanLoginActivity
import com.proyekakhir.mibu.databinding.FragmentBidanSettingsBinding
import kotlinx.coroutines.launch

class BidanSettingsFragment : Fragment() {

    private var _binding: FragmentBidanSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val bidanSettingsViewModel =
            ViewModelProvider(this).get(BidanSettingsViewModel::class.java)

        _binding = FragmentBidanSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}