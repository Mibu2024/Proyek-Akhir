package com.proyekakhir.mibu.user.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.bidan.ui.auth.BidanRegisterActivity
import com.proyekakhir.mibu.bidan.ui.auth.preferences.PreferenceManager
import com.proyekakhir.mibu.bidan.ui.mainPages.BidanMainActivity
import com.proyekakhir.mibu.databinding.ActivityOnBoardBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.auth.LoginActivity
import com.proyekakhir.mibu.user.auth.RegisterActivity
import kotlinx.coroutines.launch

class OnBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardBinding
    private lateinit var preferenceManager: PreferenceManager
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        binding.button.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        userPreference = UserPreference.getInstance(dataStore)

    }

    private fun checkLoginStatus() {
        lifecycleScope.launch {
            userPreference.getSession().collect { session ->
                if (session.token.isNotEmpty()) {
                    Log.d("UserPreference", "Token exists: ${session.token}")
                    finish()
                } else {
                    Log.d("UserPreference", "No token found")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        // check if user has login previously
        checkLoginStatus()
    }

}