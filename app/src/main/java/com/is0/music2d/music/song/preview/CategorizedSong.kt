package com.is0.music2d.music.song.preview

import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.domain.Song

data class CategorizedSong(
    val song: Song,
    val songStorageType: SongStorageType = SongStorageType.NONE,
)