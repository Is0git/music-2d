package com.is0.music2d.music.song.storage.memory.repository

import com.is0.music2d.music.song.storage.memory.mapper.InMemorySongsMapper
import com.is0.music2d.music.song.storage.memory.store.InMemorySongsStore
import com.is0.music2d.music.song.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.utils.di.qualifier.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemorySongsRepository @Inject constructor(
    private val inMemoryUserSongsMapper: InMemorySongsMapper,
    private val inMemorySongsStore: InMemorySongsStore,
    @IO private val dispatcher: CoroutineDispatcher,
) : MemorySongsRepository {
    override suspend fun watchSongs(): Flow<List<Song>> = withContext(dispatcher) {
        inMemorySongsStore.watchSongs()
            .map { songs -> songs.map(inMemoryUserSongsMapper::toSongDomain) }
            .distinctUntilChanged()
    }

    override suspend fun addSongs(songs: List<Song>) = withContext(dispatcher) {
        inMemorySongsStore.addSongs(songs.map(inMemoryUserSongsMapper::toSongEntity))
    }

    override suspend fun addSong(song: Song) {
        withContext(dispatcher) {
            inMemorySongsStore.addSong(inMemoryUserSongsMapper.toSongEntity(song))
        }
    }

    override suspend fun getSongs(): List<Song> = withContext(dispatcher) {
        inMemorySongsStore.getCurrentSongs().map(inMemoryUserSongsMapper::toSongDomain)
    }

    override suspend fun removeSong(songId: String) {
        withContext(dispatcher) {
            inMemorySongsStore.removeSong(songId)
        }
    }

    override suspend fun toggleSavedSong(song: Song) {
        withContext(dispatcher) {
            inMemorySongsStore.toggleSavedSong(inMemoryUserSongsMapper.toSongEntity(song))
        }
    }
}