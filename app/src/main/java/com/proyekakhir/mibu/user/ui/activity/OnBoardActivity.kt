package com.proyekakhir.mibu.user.ui.activity

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.BidanRegisterActivity
import com.proyekakhir.mibu.bidan.ui.mainPages.BidanMainActivity
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

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("users").document(currentUser.uid)
            docRef.get().addOnSuccessListener { document ->
                if (document != null) {
                    val role = document.getString("role")
                    if (role == "bidan") {
                        startActivity(Intent(this@OnBoardActivity, BidanMainActivity::class.java))
                        finish()
                    } else {
                        startActivity(Intent(this@OnBoardActivity, MainActivity::class.java))
                        finish()
                    }
                } else {
                    Log.d(TAG, "No such document")
                }
            }.addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        }
    }

}