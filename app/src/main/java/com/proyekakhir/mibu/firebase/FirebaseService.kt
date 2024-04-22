package com.proyekakhir.mibu.firebase

interface FirebaseService {
    fun login(email: String, password: String, onComplete: (Boolean) -> Unit)
    fun signup(fullname: String, alamat: String, email: String, noTelepon: String, umur: String, kehamilanKe: String, namaSuami: String, umurSuami: String, nik: String, password: String, onComplete: (Boolean) -> Unit)
}