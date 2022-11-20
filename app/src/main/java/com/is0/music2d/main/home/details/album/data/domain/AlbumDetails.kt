package com.is0.music2d.main.home.details.album.data.domain

import com.is0.music2d.music.album.utils.data.database.entity.AlbumWithSongsEntity
import com.is0.music2d.music.album.utils.data.domain.StoredSongsAlbum
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.utils.data.database.data.entity.toSong

data class AlbumDetails(
    val name: String,
    val storedSong: List<StoredSong>,
) {
    companion object {
        fun empty() = AlbumDetails(
            name = "",
            storedSong = emptyList(),
        )
    }
}

fun AlbumWithSongsEntity.toDetails(): AlbumDetails = AlbumDetails(
    name = this.album.name,
    storedSong = this.songs.map { songsEntity ->
        StoredSong(
            song = songsEntity.toSong(),
            songStorageTypes = emptyList(),
        )
    },
)

fun StoredSongsAlbum.toDetails(): AlbumDetails = AlbumDetails(
    name = album.name,
    storedSong = storedSongs,
)