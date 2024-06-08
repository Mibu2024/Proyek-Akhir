package com.proyekakhir.mibu.user.auth.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.response.LoginResponse
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class LoginViewModel(private val userRepository: UserRepository, private val userPreference: UserPreference) : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResponse>()
    val loginResult: LiveData<LoginResponse> = _loginResult

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun login(email: String, password: String) {
        val json = """
            {
                "email": "$email",
                "password": "$password"
            }
        """.trimIndent()

        val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())

        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = userRepository.login(requestBody)
                // If the login was successful, update _loginResult to notify the activity
                _loginResult.value = response

                var currentToken = ""
                var id = 0
                var nama = ""
                response.data?.token?.let { token ->
                    currentToken = token
                }
                response.data?.id?.let { userId ->
                    id = userId
                }
                response.data?.namaIbu?.let { name ->
                    nama = name
                }
                userPreference.saveSession(currentToken, id, true, nama)
            } catch (e: Exception) {
                // Handle any exceptions that occurred during the login operation.
            } finally {
                _isLoading.value = false
            }
        }
    }
}