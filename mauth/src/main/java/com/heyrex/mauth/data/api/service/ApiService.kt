package com.heyrex.mauth.data.api.service

import com.heyrex.mauth.data.api.request.AuthRequest
import com.heyrex.mauth.data.api.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    companion object {
        //Headers
        const val CONTENT_TYPE = "Content-Type: application/json"
        const val ACCEPT = "Accept: application/json"
    }

    @Headers(CONTENT_TYPE, ACCEPT)

    @POST(AuthUrls.API + AuthUrls.VERSION + AuthUrls.AUTH)
    suspend fun auth(@Body auth: AuthRequest): Response<AuthResponse>

}