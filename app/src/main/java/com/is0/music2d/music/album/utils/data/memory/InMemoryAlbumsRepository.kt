package com.is0.music2d.music.album.utils.data.memory

import com.is0.music2d.music.song.utils.data.memory.repository.MemoryUserSongsRepository
import com.is0.music2d.music.album.utils.data.AlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.memory.entity.InMemoryAlbum
import com.is0.music2d.music.album.utils.data.memory.mapper.InMemoryAlbumsMapper
import com.is0.music2d.utils.di.qualifier.Default
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryAlbumsRepository @Inject constructor(
    private val songsRepository: MemoryUserSongsRepository,
    private val inMemoryAlbumsMapper: InMemoryAlbumsMapper,
    @Default private val dispatcher: CoroutineDispatcher,
) : AlbumsRepository {
    private val inMemoryAlbums = MutableStateFlow<PersistentList<InMemoryAlbum>>(persistentListOf())

    override suspend fun watchAlbums(): Flow<List<Album>> =
        combine(inMemoryAlbums, songsRepository.watchSongs()) { albums, songs ->
            inMemoryAlbumsMapper.toAlbumDomain(
                songs = songs,
                inMemoryAlbums = albums,
            )
        }.flowOn(dispatcher)

    override suspend fun addAlbums(albums: List<Album>) {
        withContext(dispatcher) {
            inMemoryAlbums.value.let { oldAlbums ->
                val memoryAlbums = inMemoryAlbumsMapper.toMemoryAlbum(albums)
                oldAlbums.addAll(memoryAlbums)
            }.also { newAlbums -> inMemoryAlbums.emit(newAlbums) }
        }
    }

    override suspend fun addSongToAlbum(albumId: String, songId: String) {
        withContext(dispatcher) {
            val oldMemoryAlbums = inMemoryAlbums.value
            val albumIndex = oldMemoryAlbums.indexOfFirst { memoryAlbum -> memoryAlbum.id == albumId }

            if (albumIndex != -1) {
                val inMemoryAlbum: InMemoryAlbum = oldMemoryAlbums[albumIndex]

                val updatedAlbums = oldMemoryAlbums.mutate { memoryAlbums ->
                    val updatedAlbumSongsIds: List<String> = inMemoryAlbum.songsIds
                        .toPersistentList()
                        .add(songId)
                        .distinct()

                    memoryAlbums[albumIndex] = inMemoryAlbum.copy(songsIds = updatedAlbumSongsIds)
                }

                inMemoryAlbums.emit(updatedAlbums)
            }
        }
    }
}