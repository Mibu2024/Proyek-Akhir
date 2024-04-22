package com.proyekakhir.mibu.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.firebase.FirebaseRepository

class LoginViewModel(private val repository: FirebaseRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLoginSuccessful = MutableLiveData<Boolean>()
    val isLoginSuccessful: LiveData<Boolean> = _isLoginSuccessful

    fun login(email: String, password: String) {
        _isLoading.value = true
        repository.login(email, password) { isSuccessful ->
            _isLoading.value = false
            _isLoginSuccessful.value = isSuccessful
        }
    }
}