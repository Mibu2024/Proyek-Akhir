package com.proyekakhir.mibu.user.firebase

import com.google.firebase.database.DatabaseError
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.catatan.model.AddDataAnak
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model.IbuHamilData
import com.proyekakhir.mibu.bidan.ui.mainPages.ui.settings.UserData
import com.proyekakhir.mibu.user.ui.anak.model.AnakModel
import com.proyekakhir.mibu.user.ui.home.model.ArtikelModel
import com.proyekakhir.mibu.user.ui.home.model.BidanData
import com.proyekakhir.mibu.user.ui.home.model.UserModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.KesehatanModel
import com.proyekakhir.mibu.user.ui.kehamilan.model.NifasModel

interface FirebaseService {
    fun login(email: String, password: String, onComplete: (Boolean, String?) -> Unit)
    fun signup(fullname: String, alamat: String, email: String, noTelepon: String, umur: String, kehamilanKe: String, namaSuami: String, umurSuami: String, nik: String, faskesTk1: String, faskesRujukan: String, golDarah: String, pekerjaan: String,password: String, onComplete: (Boolean, String?) -> Unit)
    fun getUserData(onDataChange: (UserModel?) -> Unit, onCancelled: (Exception) -> Unit)
    fun getArtikel(onDataChange: (List<ArtikelModel>) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun getCatatanKesehatan(uid: String, onDataChanged: (ArrayList<KesehatanModel>) -> Unit, onIsEmpty: (String) -> Unit, onIsLoading: (Boolean) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun getCatatanNifas(uid: String, onDataChanged: (ArrayList<NifasModel>) -> Unit, onIsEmpty: (String) -> Unit, onIsLoading: (Boolean) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun getCatatanAnak(uid: String, onDataChanged: (ArrayList<AnakModel>) -> Unit, onIsEmpty: (String) -> Unit, onIsLoading: (Boolean) -> Unit, onCancelled: (DatabaseError) -> Unit)
    fun getAllBidan(role: String, onDataChange: (List<BidanData>) -> Unit, onCancelled: (DatabaseError) -> Unit)
}