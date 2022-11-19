package com.is0.music2d.music.song.storage.filesystem.utils.data

import com.is0.music2d.music.song.storage.filesystem.utils.data.database.dao.FilesystemSongsDao
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.FilesystemSongEntity
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.toDomain
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.toEntity
import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
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
    @IO private val dispatcher: CoroutineDispatcher,
) : SavedSongsRepository {
    override fun watchSavedSongs(): Flow<List<SavedSong>> =
        filesystemSongsDao.watchSongs()
            .map { songs -> songs.map(FilesystemSongEntity::toDomain) }
            .flowOn(dispatcher)

    override suspend fun addSavedSong(savedSong: SavedSong) {
        withContext(dispatcher) {
            filesystemSongsDao.addSong(savedSong.toEntity())
        }
    }
}