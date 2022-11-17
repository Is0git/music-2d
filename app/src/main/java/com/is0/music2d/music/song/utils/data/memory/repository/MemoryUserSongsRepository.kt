package com.is0.music2d.music.song.utils.data.memory.repository

import com.is0.music2d.music.song.utils.data.memory.entity.MemorySong
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.memory.mapper.MemorySongsMapper
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.retry
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryUserSongsRepository @Inject constructor(
    private val memoryUserSongsMapper: MemorySongsMapper,
) : UserSongsRepository {
    private val _memorySongs = MutableStateFlow<PersistentList<MemorySong>>(persistentListOf())

    override suspend fun watchSongs(): Flow<List<Song>> =
        _memorySongs.map { songs -> songs.map(memoryUserSongsMapper::toSongDomain) }
            .retry()
            .distinctUntilChanged()

    override suspend fun addSongs(songs: List<Song>) {
        val oldSongs = _memorySongs.value
        val memorySongs = songs.map(memoryUserSongsMapper::toSongEntity)

        val newSongs = oldSongs.addAll(memorySongs).distinctBy { song -> song.id }

        if (oldSongs.size != newSongs.size) {
            _memorySongs.emit(newSongs.toPersistentList())
        }
    }

    override suspend fun addSong(song: Song) {
        val currentSongs = _memorySongs.value
        val memorySong = memoryUserSongsMapper.toSongEntity(song)

        if (!currentSongs.any { it.id == memorySong.id }) {
            _memorySongs.emit(currentSongs.add(memorySong))
        }
    }
}