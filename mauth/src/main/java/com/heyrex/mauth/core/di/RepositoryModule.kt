package com.heyrex.mauth.core.di

import com.heyrex.mauth.data.api.service.ApiService
import com.heyrex.mauth.data.repository.AuthRepositoryImpl
import com.heyrex.mauth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSessionRepository(
        api: ApiService,
    ): AuthRepository {
        return AuthRepositoryImpl(
            api = api,
        )
    }
}