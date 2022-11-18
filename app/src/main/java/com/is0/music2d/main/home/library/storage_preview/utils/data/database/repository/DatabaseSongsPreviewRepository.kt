package com.is0.music2d.main.home.library.storage_preview.utils.data.database.repository

import com.is0.music2d.main.home.library.storage_preview.utils.data.database.entity.SongsPreviewEntity
import com.is0.music2d.main.home.library.storage_preview.utils.data.database.mapper.DatabaseSongsPreviewMapper
import com.is0.music2d.main.home.library.storage_preview.utils.data.database.store.DatabaseSongPreviewStore
import com.is0.music2d.main.home.library.storage_preview.utils.data.repository.StorageSongsPreviewRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DatabaseSongsPreviewRepository @Inject constructor(
    databaseSongPreviewStore: DatabaseSongPreviewStore,
    databaseSongsPreviewMapper: DatabaseSongsPreviewMapper,
) : StorageSongsPreviewRepository<SongsPreviewEntity>(
    storageSongPreviewStore = databaseSongPreviewStore,
    storageSongsPreviewMapper = databaseSongsPreviewMapper,
)