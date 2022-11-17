package com.is0.music2d.utils.event

import kotlinx.coroutines.flow.Flow


interface EventBus<T : Event> : EventReceiver<T>, EventSender<T>

interface EventReceiver<out T : Event> {
    fun listenEvents(): Flow<T>
}

interface EventSender<in T : Event> {
    suspend fun sendEvent(event: T)
}