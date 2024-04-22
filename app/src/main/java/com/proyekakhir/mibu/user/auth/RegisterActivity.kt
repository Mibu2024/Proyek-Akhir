package com.proyekakhir.mibu.user.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.BidanLoginActivity
import com.proyekakhir.mibu.user.auth.viewmodel.SignUpViewModel
import com.proyekakhir.mibu.databinding.ActivityRegisterBinding
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.activity.MainActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: SignUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(SignUpViewModel::class.java)

        binding.tvToLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnRegisterUser.setOnClickListener {
            val email = binding.userRegisterEmail.text.toString()
            val alamat = binding.userRegisterAlamat.text.toString()
            val fullname = binding.userRegisterFullname.text.toString()
            val noTelepon = binding.userRegisterNoTelepon.text.toString()
            val umur = binding.umur.text.toString()
            val kehamilanKe = binding.kehamilanke.text.toString()
            val namaSuami = binding.namaSuami.text.toString()
            val umurSuami = binding.userUmurSuami.text.toString()
            val nik = binding.userNik.text.toString()
            val pass = binding.userRegisterPassword.text.toString()
            val emailError = binding.userRegisterEmail.isError
            val passError = binding.userRegisterPassword.isError

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
            } else if (umur.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi umur", Toast.LENGTH_SHORT).show()
            } else if (pass.isNullOrEmpty()) {
                Toast.makeText(this, "Tolong isi password", Toast.LENGTH_SHORT).show()
            } else if (noTelepon.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi Nomor Telepon", Toast.LENGTH_SHORT).show()
            } else if (kehamilanKe.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi kehamilan keberapa", Toast.LENGTH_SHORT).show()
            } else if (namaSuami.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi Nama Suami", Toast.LENGTH_SHORT).show()
            } else if (umurSuami.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi Umur Suami", Toast.LENGTH_SHORT).show()
            } else if (nik.isNullOrEmpty()){
                Toast.makeText(this, "Tolong isi NIK", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.signup(fullname, alamat, email, noTelepon, umur, kehamilanKe, namaSuami, umurSuami, nik, pass)
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
                        this@RegisterActivity,
                        MainActivity::class.java
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