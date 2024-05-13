package com.proyekakhir.mibu.user.ui.activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
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

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)

        navView.itemIconTintList = colorStateList
        navView.isItemActiveIndicatorEnabled = false
        navView.itemActiveIndicatorColor = colorStateList
        navView.itemTextColor = colorStateList

        navView.labelVisibilityMode = NavigationBarView.LABEL_VISIBILITY_LABELED

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> showBottomNav()
                R.id.navigation_catatan_kehamilan -> showBottomNav()
                R.id.navigation_catatan_anak -> showBottomNav()
                R.id.navigation_settings -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        binding.navView.visibility = View.GONE
    }

    private fun showBottomNav() {
        binding.navView.visibility = View.VISIBLE
    }

//    override fun onStart() {
//        super.onStart()
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser == null) {
//            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
//            finish()
//        }
//    }
}