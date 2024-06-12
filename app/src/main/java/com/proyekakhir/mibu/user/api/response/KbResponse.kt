package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Parcelize
data class KbResponse(

	@field:SerializedName("data_layanan_kbs")
	val dataLayananKbs: List<DataLayananKbsItem?>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

data class DataLayananKbsItem(

	@field:SerializedName("id_ibu")
	val idIbu: Int? = null,

	@field:SerializedName("tekanan_darah")
	val tekananDarah: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("berat_badan")
	val beratBadan: Int? = null,

	@field:SerializedName("tanggal_kembali")
	val tanggalKembali: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("tanggal_praktik")
	val tanggalPraktik: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("jenis_kb")
	val jenisKb: String? = null,

	@field:SerializedName("keluhan")
	val keluhan: String? = null
) : Serializable
