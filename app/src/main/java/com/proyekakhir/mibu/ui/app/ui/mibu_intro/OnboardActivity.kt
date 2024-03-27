package com.proyekakhir.mibu.ui.app.ui.mibu_intro

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.data.PrefsManager
import com.proyekakhir.mibu.databinding.ActivityOnboardBinding
import com.proyekakhir.mibu.ui.app.ui.mibu_login.LoginActivity

class OnboardActivity : AppCompatActivity() {

    private lateinit var prefsManager: PrefsManager
    private lateinit var binding : ActivityOnboardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        prefsManager = PrefsManager(this)
        val btnStart = findViewById<Button>(R.id.btn_start)
        btnStart.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            prefsManager.isExampleLogin = true
            finish()
        }
    }
}