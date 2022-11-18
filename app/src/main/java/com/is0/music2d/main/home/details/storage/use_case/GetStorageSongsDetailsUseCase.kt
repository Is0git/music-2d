package com.is0.music2d.main.home.details.storage.use_case

import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong

interface GetStorageSongsDetailsUseCase {
    suspend fun getStorageSongs(): List<StorageDetailsSong>
}