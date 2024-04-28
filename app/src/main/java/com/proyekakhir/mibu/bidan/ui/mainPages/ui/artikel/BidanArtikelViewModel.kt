package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData

class BidanArtikelViewModel(val repository: FirebaseRepository) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataList = MutableLiveData<List<ArtikelData>>()
    val dataList: LiveData<List<ArtikelData>> get() = this._dataList

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
                _dataList.value = list
                onDataChange(list)
            },
            onCancelled = { error ->
                _isLoading.value = false
                onCancelled(error)
            }
        )
    }

}