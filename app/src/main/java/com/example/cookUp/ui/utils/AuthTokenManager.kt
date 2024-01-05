package com.example.cookUp.ui.utils

import android.content.Context

class AuthTokenManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("auth", Context.MODE_PRIVATE)

    companion object {
        private const val TOKEN_KEY = "auth_token"
    }

    fun saveAuthToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun getAuthToken(): String? {
        return sharedPreferences.getString(TOKEN_KEY, null)
    }

    fun clearAuthToken() {
        sharedPreferences.edit().remove(TOKEN_KEY).apply()
    }
}