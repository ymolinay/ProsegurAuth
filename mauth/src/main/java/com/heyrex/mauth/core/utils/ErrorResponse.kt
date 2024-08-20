package com.heyrex.mauth.core.utils

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code")
    var code: Int,
    @SerializedName("msg")
    var message: String
)