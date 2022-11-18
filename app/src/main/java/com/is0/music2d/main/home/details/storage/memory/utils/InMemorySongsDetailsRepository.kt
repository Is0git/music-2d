package com.is0.music2d.main.home.details.storage.memory.utils

import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.music.song.storage.memory.mapper.InMemorySongsMapper
import com.is0.music2d.music.song.storage.memory.store.InMemorySongsStore
import com.is0.music2d.utils.di.qualifier.IO
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class InMemorySongsDetailsRepository @Inject constructor(
    private val inMemorySongsStore: InMemorySongsStore,
    private val inMemorySongsMapper: InMemorySongsMapper,
    @IO private val dispatcher: CoroutineDispatcher,
) {
    suspend fun getStorageDetailsSongs(): List<StorageDetailsSong> =
        withContext(dispatcher) {
            inMemorySongsStore.getCurrentSongs().map { inMemorySong ->
                StorageDetailsSong(
                    id = inMemorySong.id,
                    song = inMemorySongsMapper.toSongDomain(inMemorySong),
                    isSaved = inMemorySong.isSaved,
                )
            }
        }
}