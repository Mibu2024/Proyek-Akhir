package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.Date

@Parcelize
data class IbuResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data_ibu_hamils")
	val dataIbuHamils: List<DataIbuHamilsItem?>? = null
) : Parcelable

@Parcelize
data class DataIbuHamilsItem(

	@field:SerializedName("nama_suami")
	val namaSuami: String? = null,

	@field:SerializedName("profile_photo")
	val profilePhoto: String? = null,

	@field:SerializedName("no_jkn_rujukan")
	val noJknRujukan: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("no_jkn_faskes_tk_1")
	val noJknFaskesTk1: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("email_verified_at")
	val emailVerifiedAt: Date? = null,

	@field:SerializedName("kehamilan_ke")
	val kehamilanKe: Int? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("pekerjaan")
	val pekerjaan: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("gol_darah")
	val golDarah: String? = null,

	@field:SerializedName("umur_ibu")
	val umurIbu: Int? = null,

	@field:SerializedName("umur_suami")
	val umurSuami: Int? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("no_telepon")
	val noTelepon: String? = null
) : Parcelable
