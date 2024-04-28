package com.proyekakhir.mibu.bidan.ui.firebase

import android.net.Uri
import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddKesehatanKehamilanData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddNifasData
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
    fun uploadCatatanNifas(uid: String, formData: AddNifasData, onComplete: (Boolean) -> Unit, onFailure: (Exception) -> Unit)
    fun uploadDataAnak(uid: String, formData: AddDataAnak, onComplete: (Boolean) -> Unit, onFailure: (Exception) -> Unit)
    fun getCatatanKesehatan(uid: String, onDataChanged: (ArrayList<AddKesehatanKehamilanData>) -> Unit, onIsEmpty: (String) -> Unit, onIsLoading: (Boolean) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun getCatatanNifas(uid: String, onDataChanged: (ArrayList<AddNifasData>) -> Unit, onIsEmpty: (String) -> Unit, onIsLoading: (Boolean) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun getCatatanAnak(uid: String, onDataChanged: (ArrayList<AddDataAnak>) -> Unit, onIsEmpty: (String) -> Unit, onIsLoading: (Boolean) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun deleteArtikel(artikelId: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)
    fun updateArtikel(itemKey: String, updatedArtikel: ArtikelData, onComplete: (Boolean) -> Unit)
    fun updateKesehatan(uid: String, itemKey: String, updatedKesehatan: AddKesehatanKehamilanData, onComplete: (Boolean) -> Unit)
    fun deleteKesehatan(uid: String, itemKey: String, onSuccess: () -> Unit, onFailure: (Exception) -> Unit)
}