package com.example.cookUp.retrofit

import com.example.cookUp.retrofit.dto.AuthResponse
import com.example.cookUp.retrofit.dto.LoginRequest
import com.example.cookUp.retrofit.dto.Recipe
import com.example.cookUp.retrofit.dto.Recipes
import com.example.cookUp.retrofit.dto.SignUpRequest
import com.example.cookUp.retrofit.dto.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface Api {
    @GET("hello")
    suspend fun hello(): String

    @POST("signUp")
    suspend fun signUp(@Body() signUpRequest: SignUpRequest): User

    @POST("login")
    suspend fun login(@Body() loginRequest: LoginRequest): AuthResponse

    @GET("user")
    suspend fun getUser(@Header("token") token: String): User

    @DELETE("user")
    suspend fun deleteUser(@Header("token") token: String): User

    @GET("recipes")
    suspend fun getRecipes(): Recipes
}