package com.proyekakhir.mibu.user.di

import android.content.Context
import com.proyekakhir.mibu.user.api.ApiConfig
import com.proyekakhir.mibu.user.api.UserPreference
import com.proyekakhir.mibu.user.api.UserRepository
import com.proyekakhir.mibu.user.api.dataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val pref = UserPreference.getInstance(context.dataStore)
        val user = runBlocking {
            pref.getSession().first()
        }
        val apiService = ApiConfig.getApiService(user.token)
        return UserRepository.getInstance(apiService)
    }
}