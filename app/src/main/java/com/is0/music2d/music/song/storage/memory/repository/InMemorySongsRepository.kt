package com.is0.music2d.music.song.storage.memory.repository

import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.storage.memory.mapper.InMemorySongsMapper
import com.is0.music2d.music.song.storage.memory.store.InMemorySongsStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemorySongsRepository @Inject constructor(
    private val inMemoryUserSongsMapper: InMemorySongsMapper,
    private val inMemorySongsStore: InMemorySongsStore,
) : UserSongsRepository {

    override suspend fun watchSongs(): Flow<List<Song>> =
        inMemorySongsStore.watchSongs()
            .map { songs -> songs.map(inMemoryUserSongsMapper::toSongDomain) }
            .distinctUntilChanged()

    override suspend fun addSongs(songs: List<Song>) =
        inMemorySongsStore.addSongs(songs.map(inMemoryUserSongsMapper::toSongEntity))

    override suspend fun addSong(song: Song) {
        inMemorySongsStore.addSong(inMemoryUserSongsMapper.toSongEntity(song))
    }
}