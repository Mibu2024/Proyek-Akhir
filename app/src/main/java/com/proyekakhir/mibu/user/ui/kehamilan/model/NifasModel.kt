package com.proyekakhir.mibu.user.ui.kehamilan.model

import java.io.Serializable

data class NifasModel(
    val tanggalPeriksa: String? = "",
    val kunjunganKe: String? = "",
    val periksaAsi: String? = "",
    val periksaPendarahan: String? = "",
    val periksaJalanLahir: String? = "",
    val vitaminA: String? = "",
    val masalah: String? = "",
    val tindakan: String? = "",
    val uid: String? = "",
    val nama: String? = "",
    var key: String? = "",
    var firstChildKey: String? = "",
) : Serializable
