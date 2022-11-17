package com.is0.music2d.music.album.utils.data.memory.entity

import com.is0.music2d.music.song.storage.memory.entity.InMemorySong

data class InMemoryAlbum(
    val id: String,
    val name: String,
    val songs: List<InMemorySong>,
)