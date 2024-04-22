package com.proyekakhir.mibu.bidan.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.viewmodel.BidanSignUpViewModel
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.databinding.ActivityBidanRegisterBinding

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
                Toast.makeText(
                    applicationContext,
                    R.string.sign_up_success,
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(
                    Intent(
                        this@BidanRegisterActivity,
                        BidanLoginActivity::class.java
                    )
                )
                finish()
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