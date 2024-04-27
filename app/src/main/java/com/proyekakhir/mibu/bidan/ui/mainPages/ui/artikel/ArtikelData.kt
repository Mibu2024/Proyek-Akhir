package com.proyekakhir.mibu.bidan.ui.mainPages.ui.artikel

import java.io.Serializable

data class ArtikelData(
    val judul: String? = "",
    val isiArtikel: String? = "",
    val imageUrl: String? = "",
    val uid: String? = "",
    val tanggal: String? = "",
    val nama: String? = ""
) : Serializable
