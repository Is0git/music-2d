package com.is0.music2d.utils.event

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

open class EventBusImpl<T : Event> : EventBus<T> {
    private val sharedFlow = MutableSharedFlow<T>(0)

    override fun listenEvents(): Flow<T> = sharedFlow.onEach { Timber.d("Received event: $it") }

    override suspend fun sendEvent(event: T) {
        Timber.d("Sending event:  $event")
        sharedFlow.emit(event)
    }
}