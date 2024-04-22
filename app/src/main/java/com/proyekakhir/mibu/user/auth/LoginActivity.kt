package com.proyekakhir.mibu.user.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvToSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }
}