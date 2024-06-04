package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class RegisterResponse(

	@field:SerializedName("data")
	val data: DataRegister? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class User(

	@field:SerializedName("nama_suami")
	val namaSuami: String? = null,

	@field:SerializedName("no_jkn_rujukan")
	val noJknRujukan: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("no_jkn_faskes_tk_1")
	val noJknFaskesTk1: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("kehamilan_ke")
	val kehamilanKe: String? = null,

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
	val umurIbu: String? = null,

	@field:SerializedName("umur_suami")
	val umurSuami: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("no_telepon")
	val noTelepon: String? = null
) : Parcelable

@Parcelize
data class DataRegister(

	@field:SerializedName("user")
	val user: User? = null,

	@field:SerializedName("token")
	val token: String? = null
) : Parcelable
