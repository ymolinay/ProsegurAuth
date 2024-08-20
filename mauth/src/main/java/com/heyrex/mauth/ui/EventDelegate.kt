package com.heyrex.mauth.ui

import kotlinx.coroutines.flow.Flow

interface EventDelegate<E> {
    val viewEvents: Flow<E?>
    suspend fun sendEvent(event:E)
    suspend fun resetEvent()
}