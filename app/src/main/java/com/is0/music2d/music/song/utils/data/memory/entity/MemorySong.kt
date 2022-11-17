package com.is0.music2d.music.song.utils.data.memory.entity

import com.is0.music2d.music.album.artist.utils.data.domain.Artist
import com.is0.music2d.music.song.utils.data.domain.SongSize

data class MemorySong(
    val id: String,
    val title: String,
    val songSize: SongSize,
    val artist: Artist,
    val durationMillis: Long,
    val imageUrl: String,
)