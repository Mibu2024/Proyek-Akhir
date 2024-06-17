package com.proyekakhir.mibu.user.auth

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.R
import com.proyekakhir.mibu.databinding.ActivityLoginBinding
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.dataStore
import com.proyekakhir.mibu.user.auth.viewmodel.LoginViewModel
import com.proyekakhir.mibu.user.factory.ViewModelFactory
import com.proyekakhir.mibu.user.ui.activity.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val firebaseAuth = FirebaseAuth.getInstance()
    private lateinit var progressDialog: ProgressDialog
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var userPreference: UserPreference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userPreference = UserPreference.getInstance(dataStore)

        // Initialize ProgressDialog
        progressDialog = ProgressDialog(this).apply {
            setMessage("Logging In...")
            setCancelable(false)
        }

        binding.tvToSignup.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        binding.btnLoginUser.setOnClickListener {
            val email = binding.userLoginEmail.text.toString()
            val pass = binding.userLoginPassword.text.toString()
            val emailError = binding.userLoginEmail.isError
            val passError = binding.userLoginPassword.isError

            if (email.isEmpty()) {
                Toast.makeText(this, R.string.alert_email_empty, Toast.LENGTH_SHORT).show()
            } else if (pass.isEmpty()) {
                Toast.makeText(this, R.string.alert_pass_empty, Toast.LENGTH_SHORT).show()
            } else if (emailError) {
                Toast.makeText(this, R.string.alert_email_error, Toast.LENGTH_SHORT).show()
            } else if (passError) {
                Toast.makeText(this, R.string.alert_pass_error, Toast.LENGTH_SHORT).show()
            } else {
                progressDialog.show()
                viewModel.login(email, pass)
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.pbLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
            if (!isLoading) {
                progressDialog.dismiss()
            }
        }

        viewModel.loginResult.observe(this) { data ->
            progressDialog.dismiss()
            if (data.data?.token != null) {
                progressDialog.dismiss()
                val email = binding.userLoginEmail.text.toString()
                alertLoginSuccess(getString(R.string.success_login), email)
            } else {
                progressDialog.dismiss()
                Toast.makeText(baseContext, R.string.sign_up_failed, Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.isLoading.observe(this) { isLoading ->
            binding.pbLogin.visibility = if (isLoading) View.VISIBLE else View.GONE
        }
    }

    private fun alertLoginSuccess(titleFill: String, descFill: String) {
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
            startActivity(Intent(this@LoginActivity, MainActivity::class.java))
            finish()
            dialog.dismiss()
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }
}