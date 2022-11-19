package com.is0.music2d.main.home.library.storage_preview.utils.data.memory.store

import com.is0.music2d.main.home.library.storage_preview.utils.data.memory.entity.InMemorySongStoragePreview
import com.is0.music2d.main.home.library.storage_preview.utils.data.repository.StorageSongPreviewStore
import com.is0.music2d.music.song.utils.data.memory.store.InMemorySongsStore
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class InMemorySongPreviewStore @Inject constructor(
    private val inMemorySongsStore: InMemorySongsStore,
) : StorageSongPreviewStore<InMemorySongStoragePreview> {
    override suspend fun watchStorageSongsPreview(): Flow<InMemorySongStoragePreview> =
        inMemorySongsStore.watchSongs().map { inMemorySongs ->
            inMemorySongs.sumOf { it.durationMillis }
        }.map { totalDuration -> InMemorySongStoragePreview(totalDuration) }
}