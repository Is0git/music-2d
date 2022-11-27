package com.is0.music2d.music.song.utils.data.database.repository

import com.is0.music2d.music.song.utils.data.database.SongsDao
import com.is0.music2d.music.song.utils.data.database.entity.SongEntity
import com.is0.music2d.music.song.utils.data.database.entity.toSong
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.utils.di.qualifier.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseSongsRepository @Inject constructor(
    private val songsDao: SongsDao,
    @IO private val dispatcher: CoroutineDispatcher,
) {
    fun watchSongs(): Flow<List<Song>> = songsDao.watchSongs()
        .map { songs -> songs.map(SongEntity::toSong) }
        .flowOn(dispatcher)

    suspend fun getSongsByIds(songIds: List<String>): List<Song> =
        withContext(dispatcher) {
            songsDao.getSongsByIds(songIds).map(SongEntity::toSong)
        }
}