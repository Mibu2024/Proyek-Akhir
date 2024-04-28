package com.proyekakhir.mibu.user.auth

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.BidanLoginActivity
import com.proyekakhir.mibu.bidan.ui.auth.preferences.PreferenceManager
import com.proyekakhir.mibu.bidan.ui.mainPages.BidanMainActivity
import com.proyekakhir.mibu.databinding.ActivityRegisterBinding
import com.proyekakhir.mibu.user.auth.viewmodel.SignUpViewModel
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
                val db = FirebaseFirestore.getInstance()
                val currentUser = FirebaseAuth.getInstance().currentUser?.uid
                val docRef = currentUser?.let { db.collection("users").document(it) }
                if (docRef != null) {
                    docRef.get().addOnSuccessListener { document ->
                        if (document != null) {
                            val role = document.getString("role")
                            val email = FirebaseAuth.getInstance().currentUser?.email
                            if (email != null) {
                                if (role != null) {
                                    alertRegisterSuccess(getString(R.string.welcome), email, role)
                                }
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

    private fun alertRegisterSuccess(titleFill: String, descFill: String, role: String) {
        val builder = AlertDialog.Builder(this)

        val customView = LayoutInflater.from(this)
            .inflate(R.layout.custom_layout_dialog_1_option, null)
        builder.setView(customView)

        val title = customView.findViewById<TextView>(R.id.tv_title)
        val desc = customView.findViewById<TextView>(R.id.tv_desc)
        val btnOk = customView.findViewById<Button>(R.id.ok_btn_id)

        title.text = titleFill
        desc.text = descFill

        val dialog = builder.create()

        btnOk.setOnClickListener {
            if (role == "bidan") {
                val preferenceManager = PreferenceManager(this)
                preferenceManager.setUserRole("bidan")
                startActivity(Intent(this@RegisterActivity, BidanMainActivity::class.java))
                finish()
            } else {
                val preferenceManager = PreferenceManager(this)
                preferenceManager.setUserRole("user")
                startActivity(Intent(this@RegisterActivity, MainActivity::class.java))
                finish()
            }
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}