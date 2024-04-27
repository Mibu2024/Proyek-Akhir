package com.proyekakhir.mibu.user.ui.kehamilan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.user.firebase.FirebaseRepository
import com.proyekakhir.mibu.user.ui.anak.model.AnakModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel

class CatatanKehamilanViewModel(val repository: FirebaseRepository) : ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<String>()
    val catatanKesehatanList = MutableLiveData<ArrayList<KesehatanModel>>()
    val catatanNifasList = MutableLiveData<ArrayList<NifasModel>>()
    fun getCatatanKesehatanList(uid: String) {
        repository.getCatatanKesehatan(
            uid,
            { data ->
                catatanKesehatanList.value = data
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

    fun getCatatanNifasList(uid: String) {
        repository.getCatatanNifas(
            uid,
            { data ->
                catatanNifasList.value = data
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