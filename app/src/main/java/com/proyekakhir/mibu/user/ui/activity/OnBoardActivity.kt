package com.proyekakhir.mibu.user.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.BidanRegisterActivity
import com.proyekakhir.mibu.user.auth.RegisterActivity
import com.proyekakhir.mibu.databinding.ActivityOnBoardBinding

class OnBoardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOnBoardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnBoardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.button2.setOnClickListener {
            startActivity(Intent(this, BidanRegisterActivity::class.java))
        }

    }
}