package com.heyrex.mauth.core.utils

import com.heyrex.mauth.R

sealed class RequestFailure(message: String = "") : Throwable(message) {
    class ApiError(var code: List<Int> = ArrayList(), message: String = "") :
        RequestFailure(message)

    object NoConnectionError : RequestFailure()
    object UnknownError : RequestFailure()

    fun errorManager() =
        when (this) {
            is RequestFailure.NoConnectionError -> R.string.connection_error_message
            is RequestFailure.ApiError -> message ?: ""
            else -> R.string.default_error_message
        }
}