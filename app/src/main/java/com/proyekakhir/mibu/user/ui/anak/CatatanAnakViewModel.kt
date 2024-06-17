package com.proyekakhir.mibu.user.ui.anak

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.response.AnakResponse
import com.proyekakhir.mibu.user.api.response.ImunisasiResponse
import kotlinx.coroutines.launch

class CatatanAnakViewModel(private val repository: UserRepository) : ViewModel() {
    private val _anak = MutableLiveData<AnakResponse>()
    val anak: MutableLiveData<AnakResponse> get() = _anak

    private val _imunisasi = MutableLiveData<ImunisasiResponse>()
    val imunisasi: MutableLiveData<ImunisasiResponse> get() = _imunisasi

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getCatatanAnak()
        getCatatanImunisasi()
    }

    fun getCatatanAnak() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getAnak()
                _anak.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCatatanImunisasi() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getImunisasi()
                _imunisasi.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }
}