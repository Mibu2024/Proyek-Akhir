package com.proyekakhir.mibu.bidan.ui.auth

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.preferences.PreferenceManager
import com.proyekakhir.mibu.bidan.ui.auth.viewmodel.BidanSignUpViewModel
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.BidanMainActivity
import com.proyekakhir.mibu.databinding.ActivityBidanRegisterBinding
import com.proyekakhir.mibu.user.ui.activity.MainActivity


class BidanRegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBidanRegisterBinding
    private lateinit var viewModel: BidanSignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBidanRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(BidanSignUpViewModel::class.java)

        binding.tvToLogin.setOnClickListener {
            startActivity(Intent(this@BidanRegisterActivity, BidanLoginActivity::class.java))
        }

        binding.btnRegisterBidan.setOnClickListener {
            val email = binding.bidanRegisterEmail.text.toString()
            val alamat = binding.bidanRegisterAlamat.text.toString()
            val fullname = binding.bidanRegisterFullname.text.toString()
            val noTelepon = binding.bidanRegisterNoTelepon.text.toString()
            val str = binding.bidanRegisterNoStr.text.toString()
            val pass = binding.bidanRegisterPassword.text.toString()
            val emailError = binding.bidanRegisterEmail.isError
            val passError = binding.bidanRegisterPassword.isError

            if (passError) {
                Toast.makeText(this, "Password minimum 8 characters", Toast.LENGTH_SHORT).show()
            } else if (emailError) {
                Toast.makeText(this, "Please check your email format!", Toast.LENGTH_SHORT).show()
            } else if (email.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi email", Toast.LENGTH_SHORT).show()
            } else if (alamat.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi alamat", Toast.LENGTH_SHORT).show()
            } else if (fullname.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi nama lengkap", Toast.LENGTH_SHORT).show()
            } else if (str.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi no STR", Toast.LENGTH_SHORT).show()
            } else if (pass.isNullOrEmpty()) {
                Toast.makeText(this, "Tolong isi password", Toast.LENGTH_SHORT).show()
            } else if (noTelepon.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi Nomor Telepon", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.signup(fullname, alamat, email, noTelepon, str, pass)
            }
        }

        viewModel.isLoading.observe(this, { isLoading ->
            binding.pbRegister.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.isSignupSuccessful.observe(this, { isSuccessful ->
            if (isSuccessful) {
                val db = FirebaseFirestore.getInstance()
                val currentUser = FirebaseAuth.getInstance().currentUser?.uid
                val docRef = currentUser?.let { db.collection("users").document(it) }
                if (docRef != null) {
                    docRef.get().addOnSuccessListener { document ->
                        if (document != null) {
                            val role = document.getString("role")
                            if (role == "bidan") {
                                val preferenceManager = PreferenceManager(this)
                                preferenceManager.setUserRole("bidan")
                                startActivity(Intent(this@BidanRegisterActivity, BidanMainActivity::class.java))
                                finish()
                            } else {
                                val preferenceManager = PreferenceManager(this)
                                preferenceManager.setUserRole("user")
                                startActivity(Intent(this@BidanRegisterActivity, MainActivity::class.java))
                                finish()
                            }
                        } else {
                            Log.d(ContentValues.TAG, "No such document")
                        }
                    }.addOnFailureListener { exception ->
                        Log.d(ContentValues.TAG, "get failed with ", exception)
                    }
                }
            } else {
                Toast.makeText(
                    baseContext,
                    R.string.sign_up_failed,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}