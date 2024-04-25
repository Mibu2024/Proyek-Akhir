package com.proyekakhir.mibu.bidan.ui.firebase

import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData

interface FirebaseService {
    fun login(email: String, password: String, onComplete: (Boolean) -> Unit)
    fun signupBidan(fullname: String, alamat: String, email: String, noTelepon: String, str: String, password: String, onComplete: (Boolean) -> Unit)
    fun getAllIbuHamil(role: String, onDataChange: (List<IbuHamilData>) -> Unit, onCancelled: (DatabaseError) -> Unit)

}