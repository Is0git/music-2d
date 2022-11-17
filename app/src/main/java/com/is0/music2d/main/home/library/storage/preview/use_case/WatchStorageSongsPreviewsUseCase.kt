package com.is0.music2d.main.home.library.storage.preview.use_case

import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.main.home.library.storage.preview.utils.data.memory.repository.InMemorySongsPreviewRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class WatchStorageSongsPreviewsUseCase @Inject constructor(
    private val inMemorySongsPreviewRepository: InMemorySongsPreviewRepository,
) {
    suspend fun watchStorageSongsPreviews(): Flow<List<StorageSongsPreview>> =
        inMemorySongsPreviewRepository.watchStorageSongsPreviews().map { songsPreview -> listOf(songsPreview) }
}