package com.heyrex.mauth.data.repository

import com.heyrex.mauth.core.utils.FailureFactory
import com.heyrex.mauth.core.utils.safeCall
import com.heyrex.mauth.data.api.service.ApiService
import com.heyrex.mauth.domain.model.AuthData
import com.heyrex.mauth.domain.model.AuthResult
import com.heyrex.mauth.domain.model.User
import com.heyrex.mauth.domain.model.toModel
import com.heyrex.mauth.domain.model.toRequest
import com.heyrex.mauth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

data class AuthRepositoryImpl(
    private val api: ApiService
) : AuthRepository {

    override fun auth(user: User): Flow<Result<AuthResult?>> = flow {
        val request = api.auth(user.toAuthData().toRequest())
        val response = request.safeCall({ detailResponse ->
            detailResponse.toModel()
        })
        emit(response)
    }.catch {
        emit(FailureFactory<AuthResult>().handleException(it))
    }
}