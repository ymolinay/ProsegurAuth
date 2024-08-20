package com.heyrex.mauth.domain.usecase

import com.heyrex.mauth.domain.model.AuthResult
import com.heyrex.mauth.domain.model.Customer
import com.heyrex.mauth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AuthCustomerUseCase
@Inject constructor(private val authRepository: AuthRepository) {

    fun execute(user: String, password: String): Flow<Result<AuthResult?>> {
        val user = Customer(user = user, password = password)
        return authRepository.auth(user)
    }
}