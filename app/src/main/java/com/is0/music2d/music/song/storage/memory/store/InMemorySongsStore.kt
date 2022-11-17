package com.is0.music2d.music.song.storage.memory.store

import com.is0.music2d.music.song.storage.memory.entity.InMemorySong
import com.is0.music2d.music.song.storage.memory.event.InMemorySongEvent
import com.is0.music2d.music.song.storage.memory.event.InMemorySongEventBus
import com.is0.music2d.utils.di.qualifier.IO
import com.is0.music2d.utils.event.EventSender
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemorySongsStore @Inject constructor(
    @IO private val dispatcher: CoroutineDispatcher,
    private val inMemoryEventSender: InMemorySongEventBus,
) {
    private val inMemorySongs = MutableStateFlow<PersistentList<InMemorySong>>(persistentListOf())

    fun getCurrentSongs() = inMemorySongs.value

    fun watchSongs() = inMemorySongs.map { inMemorySong -> inMemorySong.sortedBy { it.id } }

    suspend fun addSongs(songs: List<InMemorySong>) {
        withContext(dispatcher) {
            val oldSongs = inMemorySongs.value

            val newSongs = oldSongs.addAll(songs).distinctBy { song -> song.id }

            if (oldSongs.size != newSongs.size) {
                inMemorySongs.emit(newSongs.toPersistentList())
            }
        }
    }

    suspend fun addSong(inMemorySong: InMemorySong) {
        withContext(dispatcher) {
            val currentSongs = inMemorySongs.value

            if (!currentSongs.any { it.id == inMemorySong.id }) {
                inMemorySongs.emit(currentSongs.add(inMemorySong))
            }
        }
    }

    suspend fun getSongById(songId: String): InMemorySong =
        withContext(dispatcher) {
            getCurrentSongs().first { currentSongId -> currentSongId.id == songId }
        }

    suspend fun removeSong(songId: String) {
        withContext(dispatcher) {
            launch {
                inMemorySongs.emit(
                    inMemorySongs.value.let { songs ->
                        songs.removeAll { it.id == songId }
                    },
                )
            }
            launch {
                inMemoryEventSender.sendEvent(InMemorySongEvent.SongDeleted(songId))
            }
        }
    }

    suspend fun toggleSavedSong(song: InMemorySong) {
        withContext(dispatcher) {
            val songs = inMemorySongs.value

            if (songs.any { song.id == it.id }) {
                removeSong(song.id)
            } else {
                addSong(song)
            }
        }
    }
}