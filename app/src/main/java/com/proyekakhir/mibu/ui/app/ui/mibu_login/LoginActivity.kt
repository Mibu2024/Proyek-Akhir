package com.proyekakhir.mibu.ui.app.ui.mibu_login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proyekakhir.mibu.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}