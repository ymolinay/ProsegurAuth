package com.heyrex.mauth.data.api.request

import com.google.gson.annotations.SerializedName

data class AuthRequest(
    @SerializedName("user")
    val user: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("profile")
    val profile: String,
)