package com.is0.music2d.music.song.storage.memory.di

import com.is0.music2d.music.song.storage.memory.event.InMemorySongEvent
import com.is0.music2d.music.song.storage.memory.event.InMemorySongEventBus
import com.is0.music2d.utils.event.EventReceiver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// TODO(Daniel: Find out why binding generic bindings are not working)
@Module
@InstallIn(SingletonComponent::class)
abstract class InMemoryStorageModule {
    @Binds
    @Singleton
    abstract fun bindInMemoryEventReceiver(
        inMemorySongEventBus: InMemorySongEventBus,
    ): EventReceiver<InMemorySongEvent>

    @Binds
    @Singleton
    abstract fun bindInMemoryEventSender(
        inMemorySongEventBus: InMemorySongEventBus,
    ): EventReceiver<InMemorySongEvent>
}