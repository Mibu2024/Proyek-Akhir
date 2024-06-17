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

class UserRepository private constructor(
    private val apiService: ApiService
) {
    suspend fun register(raw: RequestBody): RegisterResponse {
        return apiService.register(raw)
    }

    suspend fun login(raw: RequestBody): LoginResponse {
        return apiService.login(raw)
    }

    suspend fun getNifas(): NifasResponse {
        return apiService.getNifas()
    }

    suspend fun getIbuHamil(): IbuResponse {
        return apiService.getIbuHamil()
    }

    suspend fun getAnak(): AnakResponse {
        return apiService.getAnak()
    }

    suspend fun getImunisasi(): ImunisasiResponse {
        return apiService.getImunisasi()
    }

    suspend fun getKesehatan(): KesehatanResponse{
        return apiService.getKesehatan()
    }

    suspend fun getArtikel(): ArtikelResponse {
        return apiService.getArtikel()
    }

    suspend fun getBidan(): BidanResponse {
        return apiService.getBidan()
    }

    suspend fun getKb(): KbResponse {
        return apiService.getKb()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            apiService: ApiService
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService)
            }.also { instance = it }

    }
}