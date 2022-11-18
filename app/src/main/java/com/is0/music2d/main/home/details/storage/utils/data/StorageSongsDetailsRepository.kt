package com.is0.music2d.main.home.details.storage.utils.data

interface StorageSongsDetailsRepository {
    suspend fun getStorageDetailsSongs(): List<StorageDetailsSong>

    suspend fun toggleSavedSong(songId: String)
}