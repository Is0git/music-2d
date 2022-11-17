package com.is0.music2d.main.home.library.storage.preview.utils.data.memory

import com.is0.music2d.main.home.library.storage.preview.utils.data.memory.entity.InMemorySongStoragePreview
import com.is0.music2d.main.home.library.storage.preview.utils.data.repository.StorageSongPreviewStore
import com.is0.music2d.music.song.storage.SongStorageType
import com.is0.music2d.music.song.storage.memory.store.InMemorySongsStore
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class InMemorySongPreviewStore @Inject constructor(
    private val inMemorySongsStore: InMemorySongsStore,
) : StorageSongPreviewStore<InMemorySongStoragePreview> {
    override suspend fun watchStorageSongsPreview(): Flow<InMemorySongStoragePreview> =
        flowOf(inMemorySongsStore.getCurrentSongs()).map { inMemorySongs ->
            inMemorySongs.sumOf { it.durationMillis }
        }.map { totalDuration -> InMemorySongStoragePreview(SongStorageType.MEMORY, totalDuration) }
}