package com.proyekakhir.mibu.user.auth.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.response.RegisterResponse
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class SignUpViewModel(private val repository: UserRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val registerResponse = MutableLiveData<RegisterResponse>()
    val isRegistrationSuccessful = MutableLiveData<Boolean>()

    fun signup(raw: RequestBody) {
        viewModelScope.launch {
            isLoading.value = true
            try {
                val response: RegisterResponse = repository.register(raw)
                registerResponse.value = response
                isRegistrationSuccessful.value = true
            } catch (e: Exception) {
                // Handle the error here
                isRegistrationSuccessful.value = false
            } finally {
                isLoading.value = false
            }
        }
    }
}