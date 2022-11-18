package com.is0.music2d.main.home.details.storage.utils.data

import com.is0.music2d.music.song.utils.data.domain.Song

data class StorageDetailsSong(
    val id: String,
    val song: Song,
    val isSaved: Boolean,
)