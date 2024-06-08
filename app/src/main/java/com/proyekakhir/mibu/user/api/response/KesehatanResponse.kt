package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Parcelize
data class KesehatanResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("data_kesehatan")
	val dataKesehatan: List<DataKesehatanItem?>? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable


data class DataKesehatanItem(

	@field:SerializedName("umur_kehamilan")
	val umurKehamilan: String? = null,

	@field:SerializedName("tinggi_fundus")
	val tinggiFundus: Int? = null,

	@field:SerializedName("letak_janin")
	val letakJanin: String? = null,

	@field:SerializedName("id_ibu")
	val idIbu: Int? = null,

	@field:SerializedName("id_pemeriksa")
	val idPemeriksa: Int? = null,

	@field:SerializedName("tekanan_darah")
	val tekananDarah: Int? = null,

	@field:SerializedName("nama_pemeriksa")
	val namaPemeriksa: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("berat_badan")
	val beratBadan: Int? = null,

	@field:SerializedName("foto_usg")
	val fotoUsg: String? = null,

	@field:SerializedName("denyut_jantung_janin")
	val denyutJantungJanin: Int? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("nasihat")
	val nasihat: String? = null,

	@field:SerializedName("tanggal_hpl")
	val tanggalHpl: String? = null,

	@field:SerializedName("keluhan")
	val keluhan: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("tindakan")
	val tindakan: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("kaki_bengkak")
	val kakiBengkak: String? = null,

	@field:SerializedName("hasil_lab")
	val hasilLab: String? = null
) : Serializable
