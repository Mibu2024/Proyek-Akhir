package com.proyekakhir.mibu.bidan.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository

class BidanLoginViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful

    private val _loginErrorMessage = MutableLiveData<String>()
    val loginErrorMessage: LiveData<String> = _loginErrorMessage

    fun login(email: String, password: String) {
        _isLoading.value = true
        repository.login(email, password) { isSuccessful, message ->
            if (isSuccessful) {
                val user = FirebaseAuth.getInstance().currentUser
                if (user != null && user.isEmailVerified) {
                    _isLoading.value = false
                    _isLoginSuccessful.value = true
                } else {
                    FirebaseAuth.getInstance().signOut()
                    _isLoading.value = false
                    _isLoginSuccessful.value = false
                    _loginErrorMessage.value = "Email not verified. Please check your email for verification instructions."
                }
            } else {
                _isLoading.value = false
                _isLoginSuccessful.value = false
                _loginErrorMessage.value = message ?: "Login failed. Please check your email and password."
            }
        }
    }
}
