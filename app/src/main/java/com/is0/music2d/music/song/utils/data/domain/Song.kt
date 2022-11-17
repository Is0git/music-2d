package com.is0.music2d.music.song.utils.data.domain

import com.is0.music2d.music.album.artist.utils.data.domain.Artist

data class Song(
    val id: String,
    val title: String,
    val songSize: SongSize,
    val artist: Artist,
    val durationMillis: Long,
    val imageUrl: String,
)