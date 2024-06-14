package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.model.ArtikelData

class BidanArtikelViewModel(val repository: FirebaseRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun uploadArtikel(judul: String, isiArtikel: String, selectedImageUri: Uri?, onSuccess: () -> Unit, onFailure: (Exception) -> Unit) {
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

    fun getArtikelByUser(onDataChange: (List<ArtikelData>) -> Unit, onCancelled: (DatabaseError) -> Unit) {
        _isLoading.value = true
        repository.getArtikelByUser(
            onDataChange = { list ->
                _isLoading.value = false
                onDataChange(list)
            },
            onCancelled = { error ->
                _isLoading.value = false
                onCancelled(error)
            }
        )
    }

    fun updateArtikel(itemKey: String, updatedArtikel: ArtikelData, onComplete: (Boolean) -> Unit) {
        _isLoading.value = true
        repository.updateArtikel(itemKey, updatedArtikel) { success ->
            onComplete(success)
            _isLoading.value = false
        }
    }


}