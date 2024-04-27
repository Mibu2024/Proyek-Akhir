package com.proyekakhir.mibu.user.ui.kehamilan.model

import java.io.Serializable

data class KesehatanModel(
    val tanggalPeriksa: String? = "",
    val keluhan: String? = "",
    val tekananDarah: String? = "",
    val beratBadan: String? = "",
    val umurKehamilan: String? = "",
    val tinggiFundus: String? = "",
    val letakJanin: String? = "",
    val denyutJanin: String? = "",
    val hasilLab: String? = "",
    val tindakan: String? = "",
    val kakiBengkak: String? = "",
    val nasihat: String? = "",
    val uid: String? = "",
    val nama: String? = "",
    val namaPemeriksa: String? = "",
    val tempatPeriksa: String? = "",
    val periksaSelanjutnya: String? = "",
    var key: String? = "",
    var firstChildKey: String? = "",
) : Serializable
