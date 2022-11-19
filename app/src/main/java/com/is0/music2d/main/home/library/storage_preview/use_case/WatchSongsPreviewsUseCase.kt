package com.is0.music2d.main.home.library.storage_preview.use_case

import com.is0.music2d.main.home.library.storage_preview.use_case.filesystem.WatchFilesystemSongsPreviewUseCase
import com.is0.music2d.main.home.library.storage_preview.use_case.memory.WatchMemorySongsPreviewUseCase
import com.is0.music2d.main.home.library.storage_preview.utils.data.domain.StorageSongsPreview
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
class WatchSongsPreviewsUseCase @Inject constructor(
    private val watchMemorySongsPreviewUseCase: WatchMemorySongsPreviewUseCase,
    private val watchFilesystemSongsPreviewUseCase: WatchFilesystemSongsPreviewUseCase,
) {
    suspend fun watchSongsPreviews(): Flow<List<StorageSongsPreview>> = combine(
        watchMemorySongsPreviewUseCase.watchStorageSongsPreview(),
        watchFilesystemSongsPreviewUseCase.watchStorageSongsPreview()
    ) { memorySongsPreviews, filesystemSongsPreviews ->
        listOf(memorySongsPreviews, filesystemSongsPreviews)
    }
}