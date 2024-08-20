package com.heyrex.mauth.data.repository

import com.heyrex.mauth.core.utils.RequestFailure
import com.heyrex.mauth.data.api.request.AuthRequest
import com.heyrex.mauth.data.api.response.AuthDataResponse
import com.heyrex.mauth.data.api.response.AuthResponse
import com.heyrex.mauth.data.api.service.ApiService
import com.heyrex.mauth.domain.model.User
import com.heyrex.mauth.domain.model.toModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class AuthRepositoryImplTest {

    private lateinit var apiService: ApiService
    private lateinit var authRepositoryImpl: AuthRepositoryImpl

    @Before
    fun setUp() {
        apiService = mockk()
        authRepositoryImpl = AuthRepositoryImpl(apiService)
    }

    @Test
    fun `auth should return success result when api call is successful`() = runTest {
        val user = ""
        val password = ""
        val profile = User.Profile.CUSTOMER

        val itemResponse =
            AuthResponse(data = AuthDataResponse(accessToken = "", user = ""))
        val response = mockk<Response<AuthResponse>> {
            every { body() } returns itemResponse
            every { isSuccessful } returns true
        }

        coEvery {
            apiService.auth(AuthRequest(user = user, password = password, profile = profile.value))
        } returns response

        val result =
            authRepositoryImpl.auth(User(user = user, password = password, type = profile)).first()

        Assert.assertTrue(result.isSuccess)
        Assert.assertEquals(itemResponse.toModel(), result.getOrNull())
    }

    @Test
    fun `auth should return failure result when api call throws exception`() = runTest {
        val user = ""
        val password = ""
        val profile = User.Profile.CUSTOMER
        val exception = RequestFailure.UnknownError

        coEvery {
            apiService.auth(AuthRequest(user = user, password = password, profile = profile.value))
        } throws exception

        val result =
            authRepositoryImpl.auth(User(user = user, password = password, type = profile)).first()

        Assert.assertTrue(result.isFailure)
        Assert.assertEquals(exception, result.exceptionOrNull())
    }

}