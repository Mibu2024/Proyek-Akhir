package com.proyekakhir.mibu.user.ui.anak

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.anak.model.AnakModel

class CatatanAnakViewModel(val repository: FirebaseRepository) : ViewModel() {
    val catatanAnakList = MutableLiveData<ArrayList<AnakModel>>()
    val isLoading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<String>()
    fun getCatatanAnakList(uid: String) {
        repository.getCatatanAnak(
            uid,
            { data ->
                catatanAnakList.value = data
            },
            { isEmptyMessage ->
                isEmpty.value = isEmptyMessage
            },
            { loading ->
                isLoading.value = loading
            },
            { error ->
                // Handle error here
            }
        )
    }
}