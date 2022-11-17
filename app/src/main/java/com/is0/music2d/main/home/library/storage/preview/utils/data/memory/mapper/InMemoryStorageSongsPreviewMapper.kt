package com.is0.music2d.main.home.library.storage.preview.utils.data.memory.mapper

import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.main.home.library.storage.preview.utils.data.memory.entity.InMemorySongStoragePreview
import com.is0.music2d.main.home.library.storage.preview.utils.data.repository.StorageSongsPreviewMapper
import com.is0.music2d.music.song.storage.SongStorageType
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class InMemoryStorageSongsPreviewMapper @Inject constructor() : StorageSongsPreviewMapper<InMemorySongStoragePreview> {
    override fun toStoragePreviewDomain(
        storageSongsPreviewEntity: InMemorySongStoragePreview,
    ): StorageSongsPreview = StorageSongsPreview(
        songStorageType = SongStorageType.MEMORY,
        storageSongsPreviewEntity.totalDurationMillis,
    )

    override fun toStoragePreviewEntity(
        storageSongsPreview: StorageSongsPreview,
    ): InMemorySongStoragePreview = InMemorySongStoragePreview(
        storageSongsPreview.songStorageType,
        storageSongsPreview.totalDurationMillis,
    )
}