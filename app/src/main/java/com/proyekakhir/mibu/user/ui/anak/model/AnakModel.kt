package com.proyekakhir.mibu.user.ui.anak.model

import java.io.Serializable

data class AnakModel(
    val namaAnak: String? = "",
    val tanggalLahir: String? = "",
    val umur: String? = "",
    val beratBadan: String? = "",
    val dptHb1Polio2: String? = "",
    val dptHb2Polio3: String? = "",
    val dptHb3Polio4: String? = "",
    val campak: String? = "",
    val dptHb1Dosis: String? = "",
    val campakRubella1Dosis: String? = "",
    val campakRubellaDt: String? = "",
    val tetanus: String? = "",
    val namaPemeriksa: String? = "",
    val periksaSelanjutnya: String? = "",
    val uid: String? = "",
    val namaIbu: String? = "",
    var key: String? = "",
    var firstChildKey: String? = "",
) : Serializable