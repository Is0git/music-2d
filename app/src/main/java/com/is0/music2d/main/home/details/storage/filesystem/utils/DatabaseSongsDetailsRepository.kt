package com.is0.music2d.main.home.details.storage.filesystem.utils

import com.is0.music2d.main.home.details.storage.utils.data.StorageSongsDetailsRepository
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.music.song.utils.data.database.data.SongsDao
import com.is0.music2d.music.song.utils.data.memory.repository.entity.toSong
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DatabaseSongsDetailsRepository @Inject constructor(
    private val songsDao: SongsDao,
) : StorageSongsDetailsRepository {
    override suspend fun getStorageDetailsSongs(): List<StorageDetailsSong> =
        songsDao.getSongs().filter { it.isSaved }.map { songEntity ->
            StorageDetailsSong(
                id = songEntity.songId,
                song = songEntity.toSong(),
                isSaved = songEntity.isSaved,
            )
        }

    override suspend fun toggleSavedSong(songId: String) = songsDao.toggleIsSaved(songId)
}