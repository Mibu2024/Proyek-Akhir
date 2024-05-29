package com.proyekakhir.mibu.user.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.user.firebase.FirebaseRepository

class SignUpViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSignupSuccessful = MutableLiveData<Boolean>()
    val isSignupSuccessful: LiveData<Boolean> = _isSignupSuccessful

    private val _emailVerificationMessage = MutableLiveData<String>()
    val emailVerificationMessage: LiveData<String> = _emailVerificationMessage

    fun signup(fullname: String, alamat: String, email: String, noTelepon: String, umur: String, kehamilanKe: String, namaSuami: String, umurSuami: String, nik: String, faskesTk1: String, faskesRujukan: String, golDarah: String, pekerjaan: String,password: String) {
        _isLoading.value = true
        repository.signup(fullname, alamat, email, noTelepon, umur, kehamilanKe, namaSuami, umurSuami, nik, faskesTk1, faskesRujukan, golDarah, pekerjaan, password) { isSuccessful, message ->
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