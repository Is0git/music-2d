package com.is0.music2d.music.song.storage.memory.entity

import com.is0.music2d.music.utils.data.domain.Artist
import com.is0.music2d.music.song.utils.data.domain.SongSize

data class InMemorySong(
    val id: String,
    val title: String,
    val songSize: SongSize,
    val artist: Artist,
    val durationMillis: Long,
    val imageUrl: String,
    val isSaved: Boolean,
)