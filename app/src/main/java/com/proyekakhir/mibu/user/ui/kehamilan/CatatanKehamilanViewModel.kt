package com.proyekakhir.mibu.user.ui.kehamilan

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.response.KbResponse
import com.proyekakhir.mibu.user.api.response.KesehatanResponse
import com.proyekakhir.mibu.user.api.response.NifasResponse
import kotlinx.coroutines.launch

class CatatanKehamilanViewModel(val repository: UserRepository) : ViewModel() {

    private val _kesehatan = MutableLiveData<KesehatanResponse>()
    val kesehatan: MutableLiveData<KesehatanResponse> get() = _kesehatan

    private val _nifas = MutableLiveData<NifasResponse>()
    val nifas: LiveData<NifasResponse> get() = _nifas

    private val _kb = MutableLiveData<KbResponse>()
    val kb: LiveData<KbResponse> get() = _kb

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getCatatanKehamilan()
        getCatatanNifas()
        getCatatanKb()
    }

    fun getCatatanKehamilan() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getKesehatan()
                _kesehatan.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCatatanNifas() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getNifas()
                _nifas.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCatatanKb() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getKb()
                _kb.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }
}