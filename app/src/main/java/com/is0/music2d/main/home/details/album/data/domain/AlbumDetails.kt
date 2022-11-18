package com.is0.music2d.main.home.details.album.data.domain

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.utils.data.domain.Song

data class AlbumDetails(
    val name: String,
    val songs: List<Song>,
) {
    companion object {
        fun empty() = AlbumDetails(
            name = "",
            songs = emptyList(),
        )
    }
}

fun Album.toDetails(): AlbumDetails = AlbumDetails(
    name = name,
    songs = songs,
)