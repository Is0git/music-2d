package com.is0.music2d.main.home.details.album.use_case

import com.is0.music2d.main.home.details.album.data.database.DatabaseAlbumDetailsRepository
import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.album.data.memory.MemoryAlbumDetailsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@ViewModelScoped
class WatchAlbumDetailsUseCase @Inject constructor(
    private val memoryAlbumDetailsRepository: MemoryAlbumDetailsRepository,
    private val databaseAlbumDetailsRepository: DatabaseAlbumDetailsRepository,
) {
    suspend fun watchAlbumDetails(albumId: String): Flow<AlbumDetails> =
        combine(
            memoryAlbumDetailsRepository.watchAlbumDetails(albumId).onStart { emit(null) },
            databaseAlbumDetailsRepository.watchAlbumDetails(albumId).onStart { emit(null) }
        )
        { memoryDetails, databaseDetails ->
            AlbumDetails(
                name = memoryDetails?.name ?: databaseDetails?.name.orEmpty(),
                songs = memoryDetails?.songs.orEmpty() + databaseDetails?.songs.orEmpty(),
            )
        }
}