package com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository

class BidanSettingsViewModel(val repository: FirebaseRepository) : ViewModel() {
    private val _userData = MutableLiveData<UserData?>()
    val userData: LiveData<UserData?> = _userData

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

}