package com.example.cookUp.retrofit.dto

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val ingredients: String,
    val tagline: String,
    val imageUrl: String,
    val userId: Int,
    val createdAt: String,
)
