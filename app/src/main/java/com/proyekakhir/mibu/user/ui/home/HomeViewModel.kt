package com.proyekakhir.mibu.user.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.UserData
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel
import com.proyekakhir.mibu.user.ui.home.model.BidanData
import com.proyekakhir.mibu.user.ui.home.model.UserModel

class HomeViewModel(val repository: FirebaseRepository) : ViewModel() {

    private val _userData = MutableLiveData<UserModel?>()
    val userData: LiveData<UserModel?> = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _dataList = MutableLiveData<List<BidanData>>()
    val dataList: LiveData<List<BidanData>> get() = this._dataList

    val error = MutableLiveData<DatabaseError>()

    init {
        fetchAllBidan("bidan")
    }

    fun fetchUserData() {
        repository.getUserData(
            onDataChange = { data ->
                _userData.value = data
            },
            onCancelled = { exception ->
                // Handle the error
            }
        )
    }

    fun getArtikelByUser(onDataChange: (List<ArtikelModel>) -> Unit, onCancelled: (DatabaseError) -> Unit) {
        _isLoading.value = true
        repository.getArtikel(
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

    fun fetchAllBidan(role: String) {
        _isLoading.value = true
        repository.getAllBidan(role,
            onDataChange = { data ->
                _dataList.value = data
                _isLoading.value = false
            },
            onCancelled = { databaseError ->
                error.value = databaseError
                _isLoading.value = false
            }
        )
    }
}