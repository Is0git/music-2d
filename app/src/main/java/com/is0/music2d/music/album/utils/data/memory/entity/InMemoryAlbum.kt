package com.is0.music2d.music.album.utils.data.memory.entity

import com.is0.music2d.music.song.utils.data.memory.entity.InMemorySong

data class InMemoryAlbum(
    val id: String,
    val name: String,
    val songs: List<InMemorySong>,
)