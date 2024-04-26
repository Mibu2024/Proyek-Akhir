package com.proyekakhir.mibu.bidan.ui.firebase

import android.net.Uri
import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.AddCatatanKesehatanFragment
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.UserData

interface FirebaseService {
    fun login(email: String, password: String, onComplete: (Boolean) -> Unit)
    fun signupBidan(fullname: String, alamat: String, email: String, noTelepon: String, str: String, password: String, onComplete: (Boolean) -> Unit)
    fun getAllIbuHamil(role: String, onDataChange: (List<IbuHamilData>) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun uploadArtikel(judul: String, isiArtikel: String, selectedImageUri: Uri?, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)
    fun getArtikelByUser(onDataChange: (List<ArtikelData>) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun getUserData(onDataChange: (UserData?) -> Unit, onCancelled: (Exception) -> Unit)
    fun uploadCatatanKesehatan(uid: String, formData: AddKesehatanKehamilanData, onComplete: (Boolean) -> Unit, onFailure: (Exception) -> Unit)

}