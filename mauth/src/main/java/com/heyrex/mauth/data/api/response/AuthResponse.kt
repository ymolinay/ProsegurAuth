package com.heyrex.mauth.data.api.response

import com.google.gson.annotations.SerializedName

data class AuthResponse(
    @SerializedName("data")
    val data: AuthDataResponse,
)

data class AuthDataResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("user")
    val user: String,
)