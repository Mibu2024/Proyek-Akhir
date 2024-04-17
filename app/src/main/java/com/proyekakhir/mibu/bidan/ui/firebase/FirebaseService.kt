package com.proyekakhir.mibu.bidan.ui.firebase

interface FirebaseService {
    fun login(email: String, password: String, onComplete: (Boolean) -> Unit)
    fun signupBidan(fullname: String, alamat: String, email: String, noTelepon: String, str: String, password: String, onComplete: (Boolean) -> Unit)
}