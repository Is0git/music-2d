package com.is0.music2d.main.home.library.category.utils.data.domain

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong

data class SongsCategory(
    val id: String,
    val name: String,
    val songs: List<StoredSong>,
)

fun Album.toSongCategory(): SongsCategory = SongsCategory(
    id = id,
    name = name,
    songs = songs.map { song ->
        StoredSong(
            song = song,
            songStorageTypes = emptyList(),
        )
    }
)