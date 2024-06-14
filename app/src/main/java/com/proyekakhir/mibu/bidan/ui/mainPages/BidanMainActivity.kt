package com.proyekakhir.mibu.bidan.ui.mainPages

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.BidanLoginActivity
import com.proyekakhir.mibu.databinding.ActivityBidanMainBinding

class BidanMainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBidanMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBidanMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNav()
    }

    private fun setupBottomNav() {
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val colorStateList = ColorStateList(
            arrayOf(
                intArrayOf(android.R.attr.state_checked),
                intArrayOf(-android.R.attr.state_checked)
            ),
            intArrayOf(
                ContextCompat.getColor(this, R.color.navbar_green),
                ContextCompat.getColor(this, R.color.default_item)
            )
        )

        val navController = findNavController(R.id.nav_host_fragment_activity_bidan_main)
        navView.setupWithNavController(navController)

        navView.itemIconTintList = colorStateList
        navView.isItemActiveIndicatorEnabled = false
        navView.itemActiveIndicatorColor = colorStateList
        navView.itemTextColor = colorStateList

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home, R.id.navigation_artikel, R.id.navigation_settings -> {
                    // Post the operation to the main thread's message queue
                    Handler(Looper.getMainLooper()).post {
                        // Clear the back stack
                        val backStackCount = supportFragmentManager.backStackEntryCount
                        for (i in 0 until backStackCount) {
                            supportFragmentManager.popBackStackImmediate()
                        }
                    }

                    // Show the bottom navigation
                    showBottomNav()
                }

                else -> {
                    // Hide the bottom navigation
                    hideBottomNav()
                }
            }
        }
    }

    private fun hideBottomNav() {
        binding.navView.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.navView.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser == null) {
            startActivity(Intent(this@BidanMainActivity, BidanLoginActivity::class.java))
            finish()
        }
    }

}