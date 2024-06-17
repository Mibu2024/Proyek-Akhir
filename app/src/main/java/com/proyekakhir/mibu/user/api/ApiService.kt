package com.proyekakhir.mibu.user.api

import com.proyekakhir.mibu.user.api.response.AnakResponse
import com.proyekakhir.mibu.user.api.response.ArtikelResponse
import com.proyekakhir.mibu.user.api.response.BidanResponse
import com.proyekakhir.mibu.user.api.response.IbuResponse
import com.proyekakhir.mibu.user.api.response.ImunisasiResponse
import com.proyekakhir.mibu.user.api.response.KbResponse
import com.proyekakhir.mibu.user.api.response.KesehatanResponse
import com.proyekakhir.mibu.user.api.response.LoginResponse
import com.proyekakhir.mibu.user.api.response.NifasResponse
import com.proyekakhir.mibu.user.api.response.RegisterResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("login")
    suspend fun login(
        @Body raw: RequestBody
    ): LoginResponse

    @POST("register")
    suspend fun register(
        @Body raw: RequestBody
    ): RegisterResponse

    @GET("nifas")
    suspend fun getNifas(): NifasResponse

    @GET("catatan-kesehatan")
    suspend fun getKesehatan(): KesehatanResponse

    @GET("ibu-hamil")
    suspend fun getIbuHamil(): IbuResponse

    @GET("anak")
    suspend fun getAnak(): AnakResponse

    @GET("imunisasi")
    suspend fun getImunisasi(): ImunisasiResponse

    @GET("artikel")
    suspend fun getArtikel(): ArtikelResponse

    @GET("bidan")
    suspend fun getBidan(): BidanResponse

    @GET("layanan-kb")
    suspend fun getKb(): KbResponse
}