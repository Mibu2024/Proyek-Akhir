package com.proyekakhir.mibu.ui.app.ui.mibu_intro

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.proyekakhir.mibu.data.PrefsManager
import com.proyekakhir.mibu.databinding.ActivitySplashBinding
import com.proyekakhir.mibu.ui.app.ui.mibu_home.HomeActivity
import com.proyekakhir.mibu.ui.app.ui.mibu_login.LoginActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val prefs = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("isFirstRun", true)

        lifecycleScope.launch {
            delay(2000)

            val intent = if (isFirstRun) {
                Intent(this@SplashScreenActivity, OnboardActivity::class.java)
            } else {
                val prefsManager = PrefsManager(this@SplashScreenActivity)
                if (prefsManager.isExampleLogin) {
                    Intent(this@SplashScreenActivity, HomeActivity::class.java)
                } else {
                    Intent(this@SplashScreenActivity, LoginActivity::class.java)
                }
            }

            startActivity(intent)
            finish()
        }

        // Tandai aplikasi sudah dijalankan jika ini adalah pertama kalinya
        if (isFirstRun) {
            prefs.edit().putBoolean("isFirstRun", false).apply()
        }
    }
}