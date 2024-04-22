package com.proyekakhir.mibu.user.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.proyekakhir.mibu.user.auth.viewmodel.LoginViewModel
import com.proyekakhir.mibu.user.auth.viewmodel.SignUpViewModel
import com.proyekakhir.mibu.user.firebase.FirebaseRepository

class ViewModelFactory(private val repository: FirebaseRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}