package com.is0.music2d.music.song.utils.data.memory.event

import com.is0.music2d.utils.event.EventBusImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemorySongEventBus @Inject constructor() : EventBusImpl<InMemorySongEvent>()