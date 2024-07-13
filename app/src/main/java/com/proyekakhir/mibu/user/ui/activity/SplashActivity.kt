package com.proyekakhir.mibu.user.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, OnBoardActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)

        userPreference = UserPreference.getInstance(dataStore)
    }

    private fun checkLoginStatus() {
        lifecycleScope.launch {
            userPreference.getSession().collect { session ->
                if (session.token.isNotEmpty()) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
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