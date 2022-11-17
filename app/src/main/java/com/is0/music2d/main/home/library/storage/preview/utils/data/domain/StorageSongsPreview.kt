package com.is0.music2d.main.home.library.storage.preview.utils.data.domain

import com.is0.music2d.music.song.storage.SongStorageType

data class StorageSongsPreview(
    val songStorageType: SongStorageType,
    val totalDurationMillis: Long,
)