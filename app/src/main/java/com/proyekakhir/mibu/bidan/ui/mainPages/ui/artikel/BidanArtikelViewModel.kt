package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository

class BidanArtikelViewModel(val repository: FirebaseRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun uploadArtikel(judul: String, isiArtikel: String, selectedImageUri: Uri?, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
        if (selectedImageUri != null) {
            _isLoading.value = true
            repository.uploadArtikel(judul, isiArtikel, selectedImageUri,
                onSuccess = {
                    _isLoading.value = false
                    onSuccess()
                },
                onFailure = { e ->
                    _isLoading.value = false
                    onFailure(e)
                })
        }
    }
}