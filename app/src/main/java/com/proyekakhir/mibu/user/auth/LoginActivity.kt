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
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.preferences.PreferenceManager
import com.proyekakhir.mibu.bidan.ui.customViewBidan.EmailEditText
import com.proyekakhir.mibu.bidan.ui.mainPages.BidanMainActivity
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.databinding.ActivityLoginBinding
import com.proyekakhir.mibu.user.auth.viewmodel.LoginViewModel
import com.proyekakhir.mibu.user.ui.activity.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.tvToSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLoginUser.setOnClickListener {
            val email = binding.userLoginEmail.text.toString()
            val pass = binding.userLoginPassword.text.toString()
            val emailError = binding.userLoginEmail.isError
            val passError = binding.userLoginPassword.isError

            if (email.isNullOrEmpty()){
                Toast.makeText(this, R.string.alert_email_empty, Toast.LENGTH_SHORT).show()
            } else if (pass.isNullOrEmpty()){
                Toast.makeText(this, R.string.alert_pass_empty, Toast.LENGTH_SHORT).show()
            } else if (emailError){
                Toast.makeText(this, R.string.alert_email_error, Toast.LENGTH_SHORT).show()
            } else if (passError){
                Toast.makeText(this, R.string.alert_pass_error, Toast.LENGTH_SHORT).show()
            } else {
                viewModel.login(email, pass)
            }
        }

        viewModel.isLoading.observe(this, { isLoading ->
            binding.pbLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        viewModel.isLoginSuccessful.observe(this, { isSuccessful ->
            if (isSuccessful) {
                val email = binding.userLoginEmail.text.toString()
                alertLoginSuccess(getString(R.string.welcome), email, "user")
            } else {
                Toast.makeText(baseContext, R.string.sign_up_failed, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loginErrorMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })

        binding.userForgotPassword.setOnClickListener {
            forgotPasswordDialog()
        }

        viewModel.isLoading.observe(this, { isLoading ->
            binding.pbLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
        })
    }

    private fun forgotPasswordDialog() {
        val builder = AlertDialog.Builder(this)
        val customView = LayoutInflater.from(this).inflate(R.layout.alert_dialog_forgot_password, null)
        builder.setView(customView)
        val dialog = builder.create()

        val edEmail = customView.findViewById<EmailEditText>(R.id.ed_email_reset)
        val btnSend = customView.findViewById<Button>(R.id.btn_send)
        val close = customView.findViewById<ImageView>(R.id.iv_close_reset)
        val emailError = edEmail.isError

        btnSend.setOnClickListener {
            if (emailError){
                Toast.makeText(this, "Please check your email format!", Toast.LENGTH_SHORT).show()
            } else {
                firebaseAuth.sendPasswordResetEmail(edEmail.text.toString()).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Check your email inbox!", Toast.LENGTH_SHORT).show()
                    }
                }
                dialog.dismiss()
            }
        }

        close.setOnClickListener {
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    private fun alertLoginSuccess(titleFill: String, descFill: String, role: String) {
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
                startActivity(Intent(this@LoginActivity, BidanMainActivity::class.java))
                finish()
            } else {
                val preferenceManager = PreferenceManager(this)
                preferenceManager.setUserRole("user")
                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                finish()
            }
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
        }
    }
}