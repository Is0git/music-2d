package com.is0.music2d.main.home.details.use_case

import com.is0.music2d.main.home.details.utils.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.utils.data.memory.AlbumDetailsMemoryRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WatchAlbumDetailsUseCase @Inject constructor(
    private val albumDetailsMemoryRepository: AlbumDetailsMemoryRepository,
) {
    suspend fun watchAlbumDetails(albumId: String): Flow<AlbumDetails> =
        albumDetailsMemoryRepository.watchAlbumDetails(albumId)
}