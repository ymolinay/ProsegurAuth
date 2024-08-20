package com.heyrex.mauth.domain.usecase

import com.heyrex.mauth.domain.model.AuthResult
import com.heyrex.mauth.domain.model.Employee
import com.heyrex.mauth.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class AuthEmployeeUseCase
@Inject constructor(private val authRepository: AuthRepository) {

    fun execute(user: String, password: String): Flow<Result<AuthResult?>> {
        val user = Employee(user = user, password = password)
        return authRepository.auth(user)
    }
}