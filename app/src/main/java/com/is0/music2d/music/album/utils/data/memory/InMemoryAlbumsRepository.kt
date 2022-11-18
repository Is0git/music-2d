package com.is0.music2d.music.album.utils.data.memory

import com.is0.music2d.music.album.utils.data.AlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.memory.mapper.InMemoryAlbumsMapper
import com.is0.music2d.music.album.utils.data.memory.store.InMemoryAlbumsStore
import com.is0.music2d.utils.di.qualifier.Default
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryAlbumsRepository @Inject constructor(
    private val inMemoryAlbumStore: InMemoryAlbumsStore,
    private val inMemoryAlbumsMapper: InMemoryAlbumsMapper,
    @Default private val dispatcher: CoroutineDispatcher,
) : AlbumsRepository {
    override suspend fun watchAlbums(count: Int): Flow<List<Album>> = withContext(dispatcher) {
        inMemoryAlbumStore.watchAlbums(count)
            .map { albums -> albums.map(inMemoryAlbumsMapper::toAlbumDomain) }
            .flowOn(dispatcher)
    }

    override suspend fun addAlbums(albums: List<Album>) = withContext(dispatcher) {
        inMemoryAlbumStore.addAlbums(albums.map(inMemoryAlbumsMapper::toMemoryAlbum))
    }
}