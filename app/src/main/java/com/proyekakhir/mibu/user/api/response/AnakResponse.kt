package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class AnakResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data_anaks")
	val dataAnaks: List<DataAnaksItem?>? = null
) : Parcelable

@Parcelize
data class DataAnaksItem(

	@field:SerializedName("nama_anak")
	val namaAnak: String? = null,

	@field:SerializedName("lingkar_kepala")
	val lingkarKepala: String? = null,

	@field:SerializedName("id_ibu")
	val idIbu: Int? = null,

	@field:SerializedName("umur")
	val umur: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("berat_badan")
	val beratBadan: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("tinggi_badan")
	val tinggiBadan: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("tanggal_lahir")
	val tanggalLahir: String? = null
) : Parcelable
