package com.heyrex.mauth.domain.model

import com.heyrex.mauth.data.api.response.AuthResponse

data class AuthResult(
    val token: String,
    val user: String,
)

fun AuthResponse.toModel(): AuthResult {
    return AuthResult(
        token = data.accessToken,
        user = data.user,
    )
}