package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class LoginResponse(

	@field:SerializedName("data")
	val data: DataLogin? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
) : Parcelable

@Parcelize
data class DataLogin(

	@field:SerializedName("nik")
	val nik: String? = null,

	@field:SerializedName("nama_suami")
	val namaSuami: String? = null,

	@field:SerializedName("nama_ibu")
	val namaIbu: String? = null,

	@field:SerializedName("umur_ibu")
	val umurIbu: Int? = null,

	@field:SerializedName("umur_suami")
	val umurSuami: Int? = null,

	@field:SerializedName("kehamilan_ke")
	val kehamilanKe: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("token")
	val token: String? = null,

	@field:SerializedName("alamat")
	val alamat: String? = null,

	@field:SerializedName("no_telepon")
	val noTelepon: String? = null
) : Parcelable
