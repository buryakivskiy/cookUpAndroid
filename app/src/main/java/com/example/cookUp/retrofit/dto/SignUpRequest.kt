package com.example.cookUp.retrofit.dto

data class SignUpRequest(
    val email: String,
    val firstName: String,
    val lastName: String,
    val password: String,
)
