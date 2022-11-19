package com.is0.music2d.main.home.library.category.utils.data.domain

import com.is0.music2d.music.album.utils.data.domain.Album

data class SongsCategory(
    val id: String,
    val name: String,
    val songs: List<CategorizedSong>,
)

fun Album.toSongCategory(): SongsCategory = SongsCategory(
    id = id,
    name = name,
    songs = songs.map { song ->
        CategorizedSong(
            song = song,
            songStorageType = emptyList(),
        )
    }
)