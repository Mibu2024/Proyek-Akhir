package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class ImunisasiResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data_imunisasis")
	val dataImunisasis: List<DataImunisasisItem?>? = null
) : Parcelable

@Parcelize
data class DataImunisasisItem(

	@field:SerializedName("nama_anak")
	val namaAnak: String? = null,

	@field:SerializedName("imunisasi_campak")
	val imunisasiCampak: String? = null,

	@field:SerializedName("id_anak")
	val idAnak: Int? = null,

	@field:SerializedName("nama_pemeriksa")
	val namaPemeriksa: String? = null,

	@field:SerializedName("imunisasi_dpt_hb_hib_3_polio_4")
	val imunisasiDptHbHib3Polio4: String? = null,

	@field:SerializedName("imunisasi_campak_rubella_1_dosis")
	val imunisasiCampakRubella1Dosis: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("imunisasi_dpt_hb_hib_1_dosis")
	val imunisasiDptHbHib1Dosis: String? = null,

	@field:SerializedName("imunisasi_dpt_hb_hib_2_polio_3")
	val imunisasiDptHbHib2Polio3: String? = null,

	@field:SerializedName("imunisasi_tetanus_diphteria_td")
	val imunisasiTetanusDiphteriaTd: String? = null,

	@field:SerializedName("imunisasi_dpt_hb_hib_1_polio_2")
	val imunisasiDptHbHib1Polio2: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("imunisasi_campak_rubella_dan_dt")
	val imunisasiCampakRubellaDanDt: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null
) : Parcelable
