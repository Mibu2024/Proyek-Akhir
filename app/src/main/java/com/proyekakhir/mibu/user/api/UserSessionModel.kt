package com.proyekakhir.mibu.user.api

data class UserSessionModel(
    val token: String,
    val id: Int,
    val isLogin: Boolean = false,
    val name: String
)
