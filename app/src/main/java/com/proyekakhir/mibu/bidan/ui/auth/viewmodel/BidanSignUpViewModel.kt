package com.proyekakhir.mibu.bidan.ui.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository

class BidanSignUpViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSignupSuccessful = MutableLiveData<Boolean>()
    val isSignupSuccessful: LiveData<Boolean> = _isSignupSuccessful

    private val _emailVerificationMessage = MutableLiveData<String>()
    val emailVerificationMessage: LiveData<String> = _emailVerificationMessage

    fun signup(fullname: String, alamat: String, email: String, noTelepon: String, str: String, password: String) {
        _isLoading.value = true
        repository.signupBidan(fullname, alamat, email, noTelepon, str, password) { isSuccessful, message ->
            _isLoading.value = false
            _isSignupSuccessful.value = isSuccessful
            if (isSuccessful) {
                _emailVerificationMessage.value = "Registration successful. Please check your email to verify your account."
            } else {
                _emailVerificationMessage.value = message ?: "Registration failed. Please try again."
            }
        }
    }
}
