package com.is0.music2d.main.home.library.storage.preview.utils.data.repository

import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class StorageSongsPreviewRepository<T>(
    private val storageSongsPreviewMapper: StorageSongsPreviewMapper<T>,
    private val storageSongPreviewStore: StorageSongPreviewStore<T>
) {
    suspend fun watchStorageSongsPreviews(): Flow<StorageSongsPreview> =
        storageSongPreviewStore.watchStorageSongsPreview().map { storageSongsPreview ->
            storageSongsPreviewMapper.toStoragePreviewDomain(storageSongsPreview)
        }
}