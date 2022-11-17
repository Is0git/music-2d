package com.is0.music2d.music.album.utils.data.domain

import com.is0.music2d.music.song.utils.data.domain.Song

data class Album(
    val id: String,
    val name: String,
    val songs: List<Song>,
)