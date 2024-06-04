package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class NifasResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("data_nifas")
	val dataNifas: List<DataNifasItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataNifasItem(

	@field:SerializedName("id_ibu")
	val idIbu: Int? = null,

	@field:SerializedName("hasil_periksa_jalan_lahir")
	val hasilPeriksaJalanLahir: String? = null,

	@field:SerializedName("vitamin_a")
	val vitaminA: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("hasil_periksa_payudara")
	val hasilPeriksaPayudara: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("kunjungan_nifas")
	val kunjunganNifas: String? = null,

	@field:SerializedName("hasil_periksa_pendarahan")
	val hasilPeriksaPendarahan: String? = null,

	@field:SerializedName("tindakan")
	val tindakan: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("masalah")
	val masalah: String? = null
) : Parcelable
