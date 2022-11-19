package com.is0.music2d.main.home.library.storage_preview.utils.data.database.store

import com.is0.music2d.main.home.library.storage_preview.utils.data.database.entity.SongsPreviewEntity
import com.is0.music2d.main.home.library.storage_preview.utils.data.repository.StorageSongPreviewStore
import com.is0.music2d.music.song.utils.data.database.data.SongsDao
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class DatabaseSongPreviewStore @Inject constructor(
    private val songsDao: SongsDao,
) : StorageSongPreviewStore<SongsPreviewEntity> {
    override suspend fun watchStorageSongsPreview(): Flow<SongsPreviewEntity> =
        songsDao.watchTotalSongDuration().map { duration -> SongsPreviewEntity(duration) }
}