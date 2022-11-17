package com.is0.music2d.music.album.utils.data.memory.store

import com.is0.music2d.music.album.utils.data.memory.entity.InMemoryAlbum
import com.is0.music2d.music.song.storage.memory.entity.InMemorySong
import com.is0.music2d.music.song.storage.memory.event.InMemorySongEvent
import com.is0.music2d.music.song.storage.memory.event.InMemorySongEventBus
import com.is0.music2d.music.song.storage.memory.store.InMemorySongsStore
import com.is0.music2d.utils.di.qualifier.IO
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.mutate
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
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryAlbumsStore @Inject constructor(
    private val inMemorySongsStore: InMemorySongsStore,
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
            is InMemorySongEvent.SongDeleted -> {
                deleteSong(inMemorySongEvent.songId)
            }
        }
    }

    suspend fun watchAlbums(): Flow<List<InMemoryAlbum>> = withContext(dispatcher) {
        inMemoryAlbums
    }

    suspend fun addAlbums(inMemoryAlbums: List<InMemoryAlbum>) {
        withContext(dispatcher) {
            this@InMemoryAlbumsStore.inMemoryAlbums.value.addAll(inMemoryAlbums)
                .also { newAlbums -> this@InMemoryAlbumsStore.inMemoryAlbums.emit(newAlbums) }
        }
    }

    suspend fun addSongToAlbum(albumId: String, songId: String) {
        withContext(dispatcher) {
            val oldMemoryAlbums = inMemoryAlbums.value
            val albumIndex = oldMemoryAlbums.indexOfFirst { memoryAlbum -> memoryAlbum.id == albumId }

            if (albumIndex != -1) {
                val inMemoryAlbum: InMemoryAlbum = oldMemoryAlbums[albumIndex]

                val updatedAlbums = oldMemoryAlbums.mutate { memoryAlbums ->
                    val song = inMemorySongsStore.getSongById(songId)
                    val updatedAlbumSongs: List<InMemorySong> = inMemoryAlbum.songs
                        .toPersistentList()
                        .add(song)
                        .distinct()

                    memoryAlbums[albumIndex] = inMemoryAlbum.copy(songs = updatedAlbumSongs)
                }

                inMemoryAlbums.emit(updatedAlbums)
            }
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
}