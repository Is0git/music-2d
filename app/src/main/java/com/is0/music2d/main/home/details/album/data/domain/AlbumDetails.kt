package com.is0.music2d.main.home.details.album.data.domain

import com.is0.music2d.music.song.utils.data.domain.Song

data class AlbumDetails(
    val name: String,
    val totalDuration: Long,
    val songs: List<Song>,
) {
    companion object {
        fun empty() = AlbumDetails(
            name = "",
            totalDuration = 0,
            songs = emptyList(),
        )
    }
}