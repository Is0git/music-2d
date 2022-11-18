package com.is0.music2d.main.home.details.album.data.database

import com.is0.music2d.main.home.details.album.data.AlbumDetailsRepository
import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.album.data.domain.toDetails
import com.is0.music2d.music.album.utils.data.database.DatabaseAlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.utils.di.qualifier.IO
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class DatabaseAlbumDetailsRepository @Inject constructor(
    private val repository: DatabaseAlbumsRepository,
    @IO private val dispatcher: CoroutineDispatcher,
) : AlbumDetailsRepository {
    override suspend fun watchAlbumDetails(albumId: String): Flow<AlbumDetails?> =
        withContext(dispatcher) {
            repository.watchAlbums(count = -1).map { albums ->
                albums.firstOrNull { album -> album.id == albumId }
            }.filterNotNull().map(Album::toDetails)
        }
}