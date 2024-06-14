package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData

class BidanHomeViewModel(val repository: FirebaseRepository) : ViewModel() {
    private val _dataList = MutableLiveData<List<IbuHamilData>>()
    val dataList: LiveData<List<IbuHamilData>> get() = this._dataList

    val error = MutableLiveData<DatabaseError>()
    val isLoading = MutableLiveData<Boolean>()

    init {
        fetchAllIbuHamilWithRole("user")
    }
    fun fetchAllIbuHamilWithRole(role: String) {
        isLoading.value = true
        repository.getAllIbuHamil(role,
            onDataChange = { data ->
                _dataList.value = data
                isLoading.value = false
            },
            onCancelled = { databaseError ->
                error.value = databaseError
                isLoading.value = false
            }
        )
    }
}