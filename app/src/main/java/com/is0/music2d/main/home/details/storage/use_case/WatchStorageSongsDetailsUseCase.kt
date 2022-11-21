package com.is0.music2d.main.home.details.storage.use_case

import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import kotlinx.coroutines.flow.Flow

interface WatchStorageSongsDetailsUseCase {
    fun watchStorageSongsDetails(): Flow<List<StorageDetailsSong>>
}