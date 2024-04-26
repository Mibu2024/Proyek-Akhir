package com.proyekakhir.mibu.bidan.ui.mainPages.ui.home.model

import androidx.versionedparcelable.ParcelField
import java.io.Serializable

data class IbuHamilData(
    val alamat: String? = "",
    val email: String? = "",
    val fullname: String? = "",
    val kehamilanKe: String? = "",
    val namaSuami: String? = "",
    val nik: String? = "",
    val noTelepon: String? = "",
    val role: String? = "",
    val umur: String? = "",
    val umurSuami: String? = "",
    val uid: String? = ""
) : Serializable
