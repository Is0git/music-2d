package com.is0.music2d.music.song.storage.filesystem.utils.data

import androidx.room.withTransaction
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.dao.FilesystemSongsDao
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.FilesystemSongEntity
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.toDomain
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.toFilesystemSongEntity
import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.utils.database.AppDatabase
import com.is0.music2d.utils.di.qualifier.IO
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class FilesystemSongsRepository @Inject constructor(
    private val filesystemSongsDao: FilesystemSongsDao,
    private val appDatabase: AppDatabase,
    @IO private val dispatcher: CoroutineDispatcher,
) : SavedSongsRepository {
    override fun watchSavedSongs(): Flow<List<SavedSong>> =
        filesystemSongsDao.watchSongs()
            .map { songs -> songs.map(FilesystemSongEntity::toDomain) }
            .flowOn(dispatcher)

    override suspend fun addSavedSong(savedSong: SavedSong) {
        withContext(dispatcher) {
            filesystemSongsDao.addSong(savedSong.toFilesystemSongEntity())
        }
    }

    override suspend fun toggleSavedSong(songId: String) {
        withContext(dispatcher) {
            appDatabase.withTransaction {
                val exists = filesystemSongsDao.exists(songId)
                val song = FilesystemSongEntity(songId)
                if (exists) {
                    filesystemSongsDao.deleteSong(song)
                } else {
                    filesystemSongsDao.addSong(song)
                }
            }
        }
    }
}