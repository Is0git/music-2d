package com.is0.music2d.main.home.library.storage_preview.use_case

import com.is0.music2d.main.home.library.storage_preview.utils.data.database.repository.DatabaseSongsPreviewRepository
import com.is0.music2d.main.home.library.storage_preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.main.home.library.storage_preview.utils.data.memory.repository.InMemorySongsPreviewRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
class WatchStorageSongsPreviewsUseCase @Inject constructor(
    private val databaseSongsPreviewRepository: DatabaseSongsPreviewRepository,
    private val inMemorySongsPreviewRepository: InMemorySongsPreviewRepository,
) {
    suspend fun watchStorageSongsPreviews(): Flow<List<StorageSongsPreview>> =
        combine(
            databaseSongsPreviewRepository.watchStorageSongsPreviews(),
            inMemorySongsPreviewRepository.watchStorageSongsPreviews(),
        ) { databaseSongsPreview, memorySongsPreview ->
            listOf(databaseSongsPreview, memorySongsPreview)
        }
}