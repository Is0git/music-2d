package com.is0.music2d.main.home.details.album.data.domain

import com.is0.music2d.main.home.details.album.data.DETAILS_HEADER_IMAGES_COUNT
import com.is0.music2d.music.album.utils.data.database.entity.AlbumWithSongsEntity
import com.is0.music2d.music.album.utils.data.domain.StoredSongsAlbum
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.utils.data.database.data.entity.toSong

data class AlbumDetails(
    val name: String,
    val storedSongs: List<StoredSong>,
    val albumPreviewImages: List<String> = emptyList(),
    val totalDuration: Long = 0,
) {
    companion object {
        fun empty() = AlbumDetails(
            name = "",
            storedSongs = emptyList(),
        )
    }
}

fun AlbumWithSongsEntity.toDetails(): AlbumDetails = AlbumDetails(
    name = this.album.name,
    storedSongs = this.songs.map { songsEntity ->
        StoredSong(
            song = songsEntity.toSong(),
            songStorageTypes = emptyList(),
        )
    },
)

fun StoredSongsAlbum.toDetails(): AlbumDetails = AlbumDetails(
    name = album.name,
    storedSongs = storedSongs,
    totalDuration = album.songs.sumOf { song -> song.durationMillis },
    albumPreviewImages = album.songs.take(DETAILS_HEADER_IMAGES_COUNT).map { song -> song.imageUrl }
)