package com.is0.music2d.main.home.library.category.utils.data.presentation

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.utils.data.domain.Song

data class SongsCategory(
    val id: String,
    val name: String,
    val songs: List<Song>,
) {
    companion object {
        fun fromAlbum(album: Album): SongsCategory = SongsCategory(
            id = album.id,
            name = album.name,
            songs = album.songs,
        )
    }
}