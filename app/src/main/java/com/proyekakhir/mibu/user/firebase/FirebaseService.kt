package com.proyekakhir.mibu.user.firebase

import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel.ArtikelData
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel
import com.proyekakhir.mibu.user.ui.home.model.UserModel

interface FirebaseService {
    fun login(email: String, password: String, onComplete: (Boolean) -> Unit)
    fun signup(fullname: String, alamat: String, email: String, noTelepon: String, umur: String, kehamilanKe: String, namaSuami: String, umurSuami: String, nik: String, password: String, onComplete: (Boolean) -> Unit)
    fun getUserData(onDataChange: (UserModel?) -> Unit, onCancelled: (Exception) -> Unit)
    fun getArtikel(onDataChange: (List<ArtikelModel>) -> Unit, onCancelled: (DatabaseError) -> Unit)
}