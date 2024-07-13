package com.proyekakhir.mibu.user.api.response

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Parcelize
data class ImunisasiResponse(

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("data_imunisasis")
	val dataImunisasis: List<DataImunisasisItem?>? = null
) : Parcelable

data class DataImunisasisItem(

	@field:SerializedName("id_anak")
	val idAnak: Int? = null,

	@field:SerializedName("hepatitis_b")
	val hepatitisB: String? = null,

	@field:SerializedName("bcg")
	val bcg: String? = null,

	@field:SerializedName("rota_virus_2")
	val rotaVirus2: String? = null,

	@field:SerializedName("rota_virus_1")
	val rotaVirus1: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("pcv_1")
	val pcv1: String? = null,

	@field:SerializedName("pcv_3")
	val pcv3: String? = null,

	@field:SerializedName("pcv_2")
	val pcv2: String? = null,

	@field:SerializedName("rota_virus_3")
	val rotaVirus3: String? = null,

	@field:SerializedName("polio_tetes_4")
	val polioTetes4: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("dpt_hb_hib_lanjutan")
	val dptHbHibLanjutan: String? = null,

	@field:SerializedName("polio_tetes_2")
	val polioTetes2: String? = null,

	@field:SerializedName("polio_tetes_3")
	val polioTetes3: String? = null,

	@field:SerializedName("polio_tetes_1")
	val polioTetes1: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("campak_rubella")
	val campakRubella: String? = null,

	@field:SerializedName("nama_anak")
	val namaAnak: String? = null,

	@field:SerializedName("nama_pemeriksa")
	val namaPemeriksa: String? = null,

	@field:SerializedName("dpt_hb_hib_3")
	val dptHbHib3: String? = null,

	@field:SerializedName("dpt_hb_hib_2")
	val dptHbHib2: String? = null,

	@field:SerializedName("dpt_hb_hib_1")
	val dptHbHib1: String? = null,

	@field:SerializedName("campak_rubella_lanjutan")
	val campakRubellaLanjutan: String? = null,

	@field:SerializedName("polio_suntik_2")
	val polioSuntik2: String? = null,

	@field:SerializedName("polio_suntik_1")
	val polioSuntik1: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("japanese_encephalitis")
	val japaneseEncephalitis: String? = null
) : Serializable
