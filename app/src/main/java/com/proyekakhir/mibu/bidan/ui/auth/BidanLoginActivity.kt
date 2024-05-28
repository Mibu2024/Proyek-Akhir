package com.proyekakhir.mibu.bidan.ui.auth

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
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.bidan.ui.auth.preferences.PreferenceManager
import com.proyekakhir.mibu.bidan.ui.auth.viewmodel.BidanLoginViewModel
import com.proyekakhir.mibu.bidan.ui.customViewBidan.EmailEditText
import com.proyekakhir.mibu.bidan.ui.factory.ViewModelFactory
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.BidanMainActivity
import com.proyekakhir.mibu.databinding.ActivityBidanLoginBinding
import com.proyekakhir.mibu.user.ui.activity.MainActivity

class BidanLoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBidanLoginBinding
    private lateinit var viewModel: BidanLoginViewModel
    val firebaseAuth = FirebaseAuth.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBidanLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val repository = FirebaseRepository()
        val factory = ViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(BidanLoginViewModel::class.java)


        binding.btnLoginBidan.setOnClickListener {
            val email = binding.bidanLoginEmail.text.toString()
            val pass = binding.bidanLoginPassword.text.toString()
            val emailError = binding.bidanLoginEmail.isError
            val passError = binding.bidanLoginPassword.isError

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
                val email = binding.bidanLoginEmail.text.toString()
                alertLoginSuccess(getString(R.string.success_login), email, "bidan")
            } else {
                Toast.makeText(baseContext, R.string.sign_up_failed, Toast.LENGTH_SHORT).show()
            }
        })

        viewModel.loginErrorMessage.observe(this, { message ->
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
        })


        binding.tvToSignup.setOnClickListener {
            startActivity(Intent(this@BidanLoginActivity, BidanRegisterActivity::class.java))
        }

        binding.bidanForgotPassword.setOnClickListener {
            forgotPasswordDialog()
        }

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
                startActivity(Intent(this@BidanLoginActivity, BidanMainActivity::class.java))
                finish()
            } else {
                val preferenceManager = PreferenceManager(this)
                preferenceManager.setUserRole("user")
                startActivity(Intent(this@BidanLoginActivity, MainActivity::class.java))
                finish()
            }
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
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

    override fun onStart() {
        super.onStart()
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            startActivity(Intent(this@BidanLoginActivity, BidanMainActivity::class.java))
            finish()
        }
    }
}