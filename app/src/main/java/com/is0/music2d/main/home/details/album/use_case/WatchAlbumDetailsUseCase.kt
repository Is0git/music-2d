package com.is0.music2d.main.home.details.album.use_case

import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.album.data.database.DatabaseAlbumDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WatchAlbumDetailsUseCase @Inject constructor(
    private val databaseAlbumDetailsRepository: DatabaseAlbumDetailsRepository,
) {
    suspend fun watchAlbumDetails(albumId: String): Flow<AlbumDetails> =
        databaseAlbumDetailsRepository.watchAlbumDetails(albumId)
}