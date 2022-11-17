package com.is0.music2d.music.song.utils.data.memory.store

import com.is0.music2d.music.song.utils.data.memory.entity.InMemorySong
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemorySongsStore @Inject constructor() {
    private val inMemorySongs = MutableStateFlow<PersistentList<InMemorySong>>(persistentListOf())

    fun getCurrentSongs() = inMemorySongs.value

    fun watchSongs() = inMemorySongs

    suspend fun addSongs(inMemorySongs: List<InMemorySong>) {
        val oldSongs = this.inMemorySongs.value

        val newSongs = oldSongs.addAll(inMemorySongs).distinctBy { song -> song.id }

        if (oldSongs.size != newSongs.size) {
            this.inMemorySongs.emit(newSongs.toPersistentList())
        }
    }

    suspend fun addSong(inMemorySong: InMemorySong) {
        val currentSongs = inMemorySongs.value

        if (!currentSongs.any { it.id == inMemorySong.id }) {
            inMemorySongs.emit(currentSongs.add(inMemorySong))
        }
    }

    suspend fun getSongById(songId: String): InMemorySong =
        getCurrentSongs().first { currentSongId -> currentSongId.id == songId }
}