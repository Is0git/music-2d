package com.is0.music2d.main.home.details.album.data.memory

import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.music.album.utils.data.memory.store.InMemoryAlbumsStore
import com.is0.music2d.utils.di.qualifier.IO
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class AlbumDetailsMemoryRepository @Inject constructor(
    private val albumsStore: InMemoryAlbumsStore,
    private val albumDetailsInMemoryMapper: AlbumDetailsInMemoryMapper,
    @IO private val dispatcher: CoroutineDispatcher,
) {
    suspend fun watchAlbumDetails(albumId: String): Flow<AlbumDetails> =
        withContext(dispatcher) {
            albumsStore.watchAlbumById(albumId).map(albumDetailsInMemoryMapper::toAlbumDetails)
        }
}