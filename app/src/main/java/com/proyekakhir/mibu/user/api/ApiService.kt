package com.proyekakhir.mibu.user.api

import com.proyekakhir.mibu.user.api.response.LoginResponse
import com.proyekakhir.mibu.user.api.response.RegisterResponse
import okhttp3.RequestBody
import retrofit2.http.Body
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
}