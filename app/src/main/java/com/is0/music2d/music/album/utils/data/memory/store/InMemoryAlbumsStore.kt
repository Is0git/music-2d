package com.is0.music2d.music.album.utils.data.memory.store

import com.is0.music2d.music.album.utils.data.memory.entity.InMemoryAlbum
import com.is0.music2d.music.song.utils.data.memory.entity.InMemorySong
import com.is0.music2d.music.song.utils.data.memory.event.InMemorySongEvent
import com.is0.music2d.music.song.utils.data.memory.event.InMemorySongEventBus
import com.is0.music2d.utils.di.qualifier.IO
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryAlbumsStore @Inject constructor(
    private val inMemorySongEventBus: InMemorySongEventBus,
    @IO private val dispatcher: CoroutineDispatcher,
) {
    private val inMemoryAlbums = MutableStateFlow<PersistentList<InMemoryAlbum>>(persistentListOf())

    private val listenInMemorySongEventsJob = Job()

    init {
        CoroutineScope(dispatcher + listenInMemorySongEventsJob).launch {
            inMemorySongEventBus.listenEvents().onEach(::handleInMemorySongEvent).collect()
        }
    }

    private suspend fun handleInMemorySongEvent(inMemorySongEvent: InMemorySongEvent) {
        when (inMemorySongEvent) {
            is InMemorySongEvent.SongDeleted -> deleteSong(inMemorySongEvent.songId)
            is InMemorySongEvent.UpdateSong -> updateSong(inMemorySongEvent.inMemorySong)
        }
    }

    suspend fun watchAlbums(count: Int = -1): Flow<List<InMemoryAlbum>> = withContext(dispatcher) {
        inMemoryAlbums.map { albums ->
            albums.map { album ->
                if (count > 0) {
                    album.copy(songs = album.songs.take(count))
                } else {
                    album
                }
            }
        }
    }

    suspend fun addAlbums(inMemoryAlbums: List<InMemoryAlbum>) {
        withContext(dispatcher) {
            this@InMemoryAlbumsStore.inMemoryAlbums.value.addAll(inMemoryAlbums)
                .also { newAlbums -> this@InMemoryAlbumsStore.inMemoryAlbums.emit(newAlbums) }
        }
    }

    suspend fun watchAlbumById(albumId: String): Flow<InMemoryAlbum> =
        withContext(dispatcher) {
            watchAlbums().map { it.firstOrNull { album -> album.id == albumId } }.filterNotNull()
        }

    private suspend fun deleteSong(songId: String) {
        withContext(dispatcher) {
            val albums = inMemoryAlbums.value
            albums.mapIndexed { albumIndex, album ->
                if (album.songs.any { song -> song.id == songId }) {
                    val songs = album.songs.toPersistentList()
                    val newSongs = songs.removeAll { song -> song.id == songId }
                    albums[albumIndex].copy(songs = newSongs)
                } else {
                    album
                }
            }
        }.also { newAlbums -> inMemoryAlbums.emit(newAlbums.toPersistentList()) }
    }

    private suspend fun updateSong(inMemorySong: InMemorySong) {
        withContext(dispatcher) {
            val albums = inMemoryAlbums.value
            val albumToUpdateIndex = albums.indexOfFirst { it.songs.any { songs -> songs.id == inMemorySong.id } }
            if (albumToUpdateIndex != -1) {
                val albumToUpdate = albums[albumToUpdateIndex]
                val newAlbum = albums[albumToUpdateIndex].copy(songs = albumToUpdate.songs.map { song ->
                    if (song.id == inMemorySong.id) {
                        inMemorySong
                    } else {
                        song
                    }
                })
                val newAlbums = albums.set(albumToUpdateIndex, newAlbum)

                inMemoryAlbums.emit(newAlbums)
            }
        }
    }
}