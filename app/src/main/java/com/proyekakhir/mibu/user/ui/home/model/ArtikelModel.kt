package com.proyekakhir.mibu.user.ui.home.model

import java.io.Serializable

data class ArtikelModel(
    val judul: String? = "",
    val isiArtikel: String? = "",
    val imageUrl: String? = "",
    val uid: String? = "",
    val tanggal: String? = "",
    val nama: String? = ""
) : Serializable
