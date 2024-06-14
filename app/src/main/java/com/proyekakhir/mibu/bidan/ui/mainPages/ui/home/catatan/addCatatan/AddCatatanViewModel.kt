package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.addCatatan

import android.net.Uri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.proyekakhir.mibu.bidan.ui.firebase.FirebaseRepository
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData

class AddCatatanViewModel(val repository: FirebaseRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val isEmpty = MutableLiveData<String>()
    val successMessage = MutableLiveData<String>()
    val hplSuccessMessage = MutableLiveData<String>()
    val errorMessage = MutableLiveData<String>()
    val hplErrorMessage = MutableLiveData<String>()
    val catatanKesehatanList = MutableLiveData<ArrayList<AddKesehatanKehamilanData>>()
    val catatanNifasList = MutableLiveData<ArrayList<AddNifasData>>()
    val catatanAnakList = MutableLiveData<ArrayList<AddDataAnak>>()

    fun uploadCatatanKesehatan(uid: String, formData: AddKesehatanKehamilanData, selectedImageUri: Uri?) {
        isLoading.value = true
        repository.uploadCatatanKesehatan(uid, formData, selectedImageUri,
            { success ->
                if (success) {
                    // Handle success case
                    successMessage.value = "Form and image uploaded successfully"
                    isLoading.value = false
                } else {
                    // Handle failure case
                    isLoading.value = false
                    errorMessage.value = "Failed to upload form and image"
                }
            },
            { exception ->
                // Handle exception
                isLoading.value = false
                errorMessage.value = exception.message ?: "An error occurred"
            })
    }


    fun uploadCatatanNifas(uid: String, formData: AddNifasData) {
        isLoading.value = true
        repository.uploadCatatanNifas(uid, formData, { success ->
            if (success) {
                // Handle success case
                successMessage.value = "Form uploaded successfully"
                isLoading.value = false
            } else {
                // Handle failure case
                isLoading.value = false
                errorMessage.value = "Failed to upload form"
            }
        }, { exception ->
            // Handle exception
            errorMessage.value = exception.message ?: "An error occurred"
        })
    }

    fun uploadCatatanAnak(uid: String, formData: AddDataAnak) {
        isLoading.value = true
        repository.uploadDataAnak(uid, formData, { success ->
            if (success) {
                // Handle success case
                successMessage.value = "Form uploaded successfully"
                isLoading.value = false
            } else {
                // Handle failure case
                isLoading.value = false
                errorMessage.value = "Failed to upload form"
            }
        }, { exception ->
            // Handle exception
            errorMessage.value = exception.message ?: "An error occurred"
        })
    }

    fun addHpl(uid: String, hplDate: String) {
        isLoading.value = true
        repository.uploadHpl(uid, hplDate, { success ->
            if (success) {
                // Handle success case
                hplSuccessMessage.value = "HPL uploaded successfully"
                isLoading.value = false
            } else {
                // Handle failure case
                isLoading.value = false
                hplErrorMessage.value = "Failed to upload HPL"
            }
        }, { exception ->
            // Handle exception
            errorMessage.value = exception.message ?: "An error occurred"
        })
    }

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

    fun updateKesehatan(uid: String, itemKey: String, updatedKesehatan: AddKesehatanKehamilanData, onComplete: (Boolean) -> Unit) {
        isLoading.value = true
        repository.updateKesehatan(uid, itemKey, updatedKesehatan) { success ->
            onComplete(success)
            isLoading.value = false
        }
    }

    fun updateNifas(uid: String, itemKey: String, updatedNifas: AddNifasData, onComplete: (Boolean) -> Unit) {
        isLoading.value = true
        repository.updateNifas(uid, itemKey, updatedNifas) { success ->
            onComplete(success)
            isLoading.value = false
        }
    }

    fun updateAnak(uid: String, itemKey: String, updatedAnak: AddDataAnak, onComplete: (Boolean) -> Unit) {
        isLoading.value = true
        repository.updateAnak(uid, itemKey, updatedAnak) { success ->
            onComplete(success)
            isLoading.value = false
        }
    }
}