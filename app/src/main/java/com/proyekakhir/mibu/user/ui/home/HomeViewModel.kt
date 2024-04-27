package com.proyekakhir.mibu.user.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.UserData
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel
import com.proyekakhir.mibu.user.ui.home.model.UserModel

class HomeViewModel(val repository: FirebaseRepository) : ViewModel() {

    private val _userData = MutableLiveData<UserModel?>()
    val userData: LiveData<UserModel?> = _userData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

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
}