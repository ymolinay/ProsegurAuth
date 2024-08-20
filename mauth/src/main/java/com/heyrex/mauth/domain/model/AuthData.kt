package com.heyrex.mauth.domain.model

import com.heyrex.mauth.data.api.request.AuthRequest

data class AuthData(
    val user: String,
    val password: String,
    val profile: String,
) {
    class Builder {
        private var user: String = ""
        private var password: String = ""
        private var profile: String = ""

        fun user(value: String) = apply { user = value }
        fun password(value: String) = apply { password = value }
        fun profile(value: String) = apply { profile = value }

        fun build() = AuthData(user, password, profile)
    }
}

    fun AuthData.toRequest() = AuthRequest(user = user, password = password, profile = profile)