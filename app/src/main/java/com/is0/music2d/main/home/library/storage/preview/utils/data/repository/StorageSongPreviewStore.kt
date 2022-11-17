package com.is0.music2d.main.home.library.storage.preview.utils.data.repository

import kotlinx.coroutines.flow.Flow

interface StorageSongPreviewStore<T> {
    suspend fun watchStorageSongsPreview(): Flow<T>
}