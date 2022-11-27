package com.is0.music2d.main.home.library.storage_preview.use_case

import com.is0.music2d.main.home.library.storage_preview.utils.data.domain.StorageSongsPreview
import kotlinx.coroutines.flow.Flow

interface WatchStorageSongsPreviewUseCase {
    fun watchStorageSongsPreview(): Flow<StorageSongsPreview>
}