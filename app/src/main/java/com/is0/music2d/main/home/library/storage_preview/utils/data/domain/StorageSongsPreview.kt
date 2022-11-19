package com.is0.music2d.main.home.library.storage_preview.utils.data.domain

import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType

data class StorageSongsPreview(
    val songStorageType: SongStorageType,
    val totalDurationMillis: Long,
)