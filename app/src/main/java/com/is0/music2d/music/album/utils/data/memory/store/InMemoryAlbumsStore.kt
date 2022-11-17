package com.is0.music2d.music.album.utils.data.memory.store

import com.is0.music2d.music.album.utils.data.memory.entity.InMemoryAlbum
import com.is0.music2d.music.song.utils.data.memory.entity.InMemorySong
import com.is0.music2d.music.song.utils.data.memory.store.InMemorySongsStore
import com.is0.music2d.utils.di.qualifier.IO
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryAlbumsStore @Inject constructor(
    private val inMemorySongsStore: InMemorySongsStore,
    @IO private val dispatcher: CoroutineDispatcher,
) {
    private val inMemoryAlbums = MutableStateFlow<PersistentList<InMemoryAlbum>>(persistentListOf())

    fun watchAlbums(): Flow<List<InMemoryAlbum>> = inMemoryAlbums

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
}