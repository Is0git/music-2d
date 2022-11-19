package com.is0.music2d.main.home.library.category.utils.data.domain

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.main.home.library.category.utils.data.domain.CategorizedSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType

data class SongsCategory(
    val id: String,
    val name: String,
    val songs: List<CategorizedSong>,
)

fun Album.toSongCategory(): SongsCategory {
    return SongsCategory(
        id = id,
        name = name,
        songs = songs.map { song ->
            CategorizedSong(
                song = song,
                songStorageType = emptyList(),
            )
        }
    )
}