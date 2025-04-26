package com.fmt.konichi.usecase

sealed class AuthResult {
    object Idle : AuthResult()
    object Loading : AuthResult()
    data class Success(val message: String) : AuthResult()
    data class Error(val message: String) : AuthResult()
}