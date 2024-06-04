package com.proyekakhir.mibu.user.api

data class UserSessionModel(
    val token: String,
    val isLogin: Boolean = false
)
