package com.is0.music2d.utils.event

import kotlinx.coroutines.flow.Flow


interface EventBus<T : Event> {
    fun listenEvents(): Flow<T>

    suspend fun sendEvent(event: T)
}