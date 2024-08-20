package com.heyrex.mauth.domain.usecase

import com.heyrex.mauth.domain.repository.AuthRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AuthCustomerUseCaseTest {

    lateinit var sut: AuthCustomerUseCase

    @Mock
    lateinit var sessionRepository: AuthRepository

    @Before
    fun setup() {
        initInteractor()
    }

    val user: String = ""
    val password: String = ""
    private fun initInteractor() {
        sut = AuthCustomerUseCase(sessionRepository)
    }

    @Test
    @Throws(Exception::class)
    fun testOnGetSampleCallGetRemoteObjectOnlyOnce() {
        runBlocking { sut.execute(user, password) }
        verify(sessionRepository, times(1)).auth(any())
    }

}