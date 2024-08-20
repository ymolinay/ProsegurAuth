package com.heyrex.mauth.domain.repository

import com.heyrex.mauth.domain.model.AuthResult
import com.heyrex.mauth.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository{

    @Throws(Exception::class)
    fun auth(user: User): Flow<Result<AuthResult?>>
}