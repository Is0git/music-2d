package com.is0.music2d.main.home.library.category.utils.data.presentation

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.preview.CategorizedSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.domain.Song

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
                songStorageType = SongStorageType.NONE,
            )
        }
    )
}