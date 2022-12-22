package com.is0.music2d.main.home.details.song.utils.data.domain

import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.domain.SongSize
import com.is0.music2d.music.utils.data.domain.Artist

data class SongDetails(
    val id: String,
    val songTitle: String,
    val albumTitle: String,
    val songSize: SongSize,
    val artist: Artist,
    val durationMillis: Long,
    val imageUrl: String,
    val creationDate: String,
)

fun Song.toSongDetails(): SongDetails = SongDetails(
    id = id,
    songTitle = title,
    albumTitle = "",
    artist = artist,
    songSize = songSize,
    durationMillis = durationMillis,
    imageUrl = imageUrl,
    creationDate = "",
)