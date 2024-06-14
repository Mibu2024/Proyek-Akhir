package com.proyekakhir.mibu.user.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.response.ArtikelResponse
import com.proyekakhir.mibu.user.api.response.BidanResponse
import com.proyekakhir.mibu.user.api.response.IbuResponse
import kotlinx.coroutines.launch

class HomeViewModel(val repository: UserRepository) : ViewModel() {

    private val _artikel = MutableLiveData<ArtikelResponse>()
    val artikel: MutableLiveData<ArtikelResponse> get() = _artikel

    private val _hpl = MutableLiveData<IbuResponse>()
    val hpl: MutableLiveData<IbuResponse> get() = _hpl

    private val _ibu = MutableLiveData<IbuResponse>()
    val ibu: MutableLiveData<IbuResponse> get() = _ibu

    private val _bidan = MutableLiveData<BidanResponse>()
    val bidan: MutableLiveData<BidanResponse> get() = _bidan

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        getArtikel()
        getHpl()
        getBidan()
        getIbu()
    }

    fun getArtikel() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getArtikel()
                _artikel.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getHpl() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getIbuHamil()
                _hpl.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getIbu() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getIbuHamil()
                _ibu.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getBidan() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = repository.getBidan()
                _bidan.postValue(response)
            } catch (e: Exception) {
                _error.postValue(e.message)
            } finally {
                _isLoading.value = false
            }
        }
    }
}