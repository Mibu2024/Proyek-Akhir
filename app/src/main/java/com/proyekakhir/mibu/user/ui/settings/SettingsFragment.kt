package com.proyekakhir.mibu.user.ui.settings

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.BidanLoginActivity
import com.proyekakhir.mibu.databinding.FragmentSettingsBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.activity.OnBoardActivity
import com.proyekakhir.mibu.user.ui.home.HomeViewModel
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.itemLogout.setOnClickListener {
            alertLogout(getString(R.string.warning), getString(R.string.want_to_logout))
        }

        binding.itemAbout.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_settings_to_aboutFragment)
        }

        binding.itemDataPribadi.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_settings_to_dataPribadiFragment2)
        }

        return root
    }

    private fun alertLogout(titleFill: String, descFill: String) {
        val builder = AlertDialog.Builder(requireContext())
        val customView = LayoutInflater.from(requireContext())
            .inflate(R.layout.custom_layout_dialog_2_option, null)
        builder.setView(customView)
        val dialog = builder.create()

        val title = customView.findViewById<TextView>(R.id.tv_title)
        val desc = customView.findViewById<TextView>(R.id.tv_desc)
        val btnYes = customView.findViewById<Button>(R.id.yes_btn_id)
        val btnNo = customView.findViewById<Button>(R.id.no_btn_id)

        title.text = titleFill
        desc.text = descFill

        btnYes.setOnClickListener {
            lifecycleScope.launch {
                logout()
            }
            dialog.dismiss()
        }

        btnNo.setOnClickListener {
            dialog.cancel()
        }
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private suspend fun logout() {
        val dataStore = requireContext().dataStore
        val userPreference = UserPreference.getInstance(dataStore)
        userPreference.clearSession()

        // Navigate to OnBoardActivity
        val intent = Intent(requireContext(), OnBoardActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}