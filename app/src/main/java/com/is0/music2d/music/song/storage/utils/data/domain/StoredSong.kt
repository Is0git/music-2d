package com.is0.music2d.music.song.storage.utils.data.domain

import com.is0.music2d.music.song.utils.data.domain.Song

data class StoredSong(
    val song: Song,
    val songStorageTypes: List<SongStorageType> = emptyList(),
)