package com.is0.music2d.music.album.utils.data.memory

import com.is0.music2d.music.song.utils.data.memory.repository.MemoryUserSongsRepository
import com.is0.music2d.music.album.utils.data.AlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.memory.mapper.MemoryAlbumsMapper
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
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemoryAlbumsRepository @Inject constructor(
    private val songsRepository: MemoryUserSongsRepository,
    private val memoryAlbumsMapper: MemoryAlbumsMapper,
    @Default private val dispatcher: CoroutineDispatcher,
) : AlbumsRepository {
    private val _memoryAlbums = MutableStateFlow<PersistentList<MemoryAlbum>>(persistentListOf())

    override suspend fun watchAlbums(): Flow<List<Album>> =
        combine(_memoryAlbums, songsRepository.watchSongs()) { albums, songs ->
            memoryAlbumsMapper.toAlbumDomain(
                songs = songs,
                memoryAlbums = albums,
            )
        }
            .retry()
            .flowOn(dispatcher)

    override suspend fun addAlbums(albums: List<Album>) {
        withContext(dispatcher) {
            _memoryAlbums.value.let { oldAlbums ->
                val memoryAlbums = memoryAlbumsMapper.toMemoryAlbum(albums)
                oldAlbums.addAll(memoryAlbums)
            }.also { newAlbums -> _memoryAlbums.emit(newAlbums) }
        }
    }

    override suspend fun addSongToAlbum(albumId: String, songId: String) {
        withContext(dispatcher) {
            val oldMemoryAlbums = _memoryAlbums.value
            val albumIndex = oldMemoryAlbums.indexOfFirst { memoryAlbum -> memoryAlbum.id == albumId }

            if (albumIndex != -1) {
                val memoryAlbum: MemoryAlbum = oldMemoryAlbums[albumIndex]

                val updatedAlbums = oldMemoryAlbums.mutate { memoryAlbums ->
                    val updatedAlbumSongsIds: List<String> = memoryAlbum.songsIds
                        .toPersistentList()
                        .add(songId)
                        .distinct()

                    memoryAlbums[albumIndex] = memoryAlbum.copy(songsIds = updatedAlbumSongsIds)
                }

                _memoryAlbums.emit(updatedAlbums)
            }
        }
    }
}