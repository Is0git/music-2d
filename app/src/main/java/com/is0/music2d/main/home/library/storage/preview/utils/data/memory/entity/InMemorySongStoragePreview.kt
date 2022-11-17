package com.is0.music2d.main.home.library.storage.preview.utils.data.memory.entity

import com.is0.music2d.music.song.storage.SongStorageType

data class InMemorySongStoragePreview(
    val songStorageType: SongStorageType,
    val totalDurationMillis: Long,
)