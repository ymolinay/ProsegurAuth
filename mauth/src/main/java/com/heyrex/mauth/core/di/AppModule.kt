package com.heyrex.mauth.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    companion object {
        const val nameApp = "AuthApp"
    }

    @Provides
    @Singleton
    fun providesContext(context: Application): Context {
        return context.applicationContext
    }
}