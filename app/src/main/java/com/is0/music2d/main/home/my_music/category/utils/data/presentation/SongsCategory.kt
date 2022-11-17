package com.is0.music2d.main.home.my_music.category.utils.data.presentation

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.utils.data.domain.Song

data class SongsCategory(
    val name: String,
    val songs: List<Song>,
) {
    companion object {
        fun fromAlbum(album: Album): SongsCategory = SongsCategory(
            album.name,
            album.songs,
        )
    }
}