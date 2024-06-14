package com.proyekakhir.mibu.user.api

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.proyekakhir.mibu.user.ui.home.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "session")

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getSession(): Flow<UserSessionModel> {
        return dataStore.data.map { preferences ->
            UserSessionModel(
                preferences[TOKEN_KEY] ?: "",
                preferences[USER_ID] ?: 0,
                preferences[IS_LOGIN_KEY] ?: false,
                preferences[NAME] ?: ""
            )
        }
    }

    suspend fun saveSession(token: String, userId: Int, isLogin: Boolean, name: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
            preferences[USER_ID] = userId
            preferences[IS_LOGIN_KEY] = isLogin
            preferences[NAME] = name
        }
    }

    suspend fun clearSession() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val TOKEN_KEY = stringPreferencesKey("token")
        private val USER_ID = intPreferencesKey("userId")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
        private val NAME = stringPreferencesKey("name")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}