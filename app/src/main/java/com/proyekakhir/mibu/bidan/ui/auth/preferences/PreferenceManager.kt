package com.proyekakhir.mibu.bidan.ui.auth.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)

    fun setUserRole(role: String) {
        val editor = sharedPreferences.edit()
        editor.putString("UserRole", role)
        editor.apply()
    }

    fun getUserRole(): String? {
        return sharedPreferences.getString("UserRole", null)
    }

}