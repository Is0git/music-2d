package com.is0.music2d.main.home.library.storage_preview.utils.data.database.mapper

import com.is0.music2d.main.home.library.storage_preview.utils.data.database.entity.SongsPreviewEntity
import com.is0.music2d.main.home.library.storage_preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.main.home.library.storage_preview.utils.data.repository.StorageSongsPreviewMapper
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class DatabaseSongsPreviewMapper @Inject constructor() : StorageSongsPreviewMapper<SongsPreviewEntity> {
    override fun toStoragePreviewDomain(storageSongsPreviewEntity: SongsPreviewEntity): StorageSongsPreview =
        StorageSongsPreview(
            SongStorageType.FILESYSTEM,
            storageSongsPreviewEntity.totalDurationMillis,
        )

    override fun toStoragePreviewEntity(storageSongsPreview: StorageSongsPreview): SongsPreviewEntity =
        SongsPreviewEntity(storageSongsPreview.totalDurationMillis)
}