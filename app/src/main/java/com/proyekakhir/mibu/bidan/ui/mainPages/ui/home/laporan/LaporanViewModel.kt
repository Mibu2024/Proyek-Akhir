package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.laporan

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData

class LaporanViewModel(val repository: FirebaseRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<String>()
    val catatanKesehatanList = MutableLiveData<ArrayList<AddKesehatanKehamilanData>>()
    val catatanNifasList = MutableLiveData<ArrayList<AddNifasData>>()
    val catatanAnakList = MutableLiveData<ArrayList<AddDataAnak>>()
    fun allCatatanKesehatanList() {
        repository.allCatatanKesehatan(
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

    fun allCatatanNifasList() {
        repository.allCatatanNifas(
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

    fun allCatatanAnakList() {
        repository.allCatatanAnak(
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