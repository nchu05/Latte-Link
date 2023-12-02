package com.example.androidfa23.Data

data class SessionClass(
    val user_id: Int,
    val session_token: String,
    val session_expiration: String,
    val refresh_token: String
)
