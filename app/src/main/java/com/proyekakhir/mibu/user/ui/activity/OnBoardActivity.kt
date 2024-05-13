package com.proyekakhir.mibu.user.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.bidan.ui.auth.BidanRegisterActivity
import com.proyekakhir.mibu.bidan.ui.auth.preferences.PreferenceManager
import com.proyekakhir.mibu.bidan.ui.mainPages.BidanMainActivity
import com.proyekakhir.mibu.databinding.ActivityOnBoardBinding
import com.proyekakhir.mibu.user.auth.RegisterActivity

class OnBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardBinding
    private lateinit var preferenceManager: PreferenceManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceManager = PreferenceManager(this)

        binding.button.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, BidanRegisterActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val userRole = preferenceManager.getUserRole()

            if (userRole == "bidan") {
                startActivity(Intent(this@OnBoardActivity, BidanMainActivity::class.java))
                finish()
            } else if (userRole == "user") {
                startActivity(Intent(this@OnBoardActivity, MainActivity::class.java))
                finish()
            }
        }
    }

}