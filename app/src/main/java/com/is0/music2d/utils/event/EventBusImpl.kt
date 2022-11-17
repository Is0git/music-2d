package com.is0.music2d.utils.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

open class EventBusImpl<T : Event> : EventBus<T> {
    private val sharedFlow = MutableSharedFlow<T>(0)

    override fun listenEvents(): Flow<T> = sharedFlow

    override suspend fun sendEvent(event: T) {
        sharedFlow.emit(event)
    }
}