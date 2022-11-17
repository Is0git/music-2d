package com.is0.music2d.music.album.utils.data.domain

import com.is0.music2d.music.song.utils.data.domain.SongMock
import java.util.*

object AlbumMock {
    private val albumNames = listOf(
        "Dedication",
        "Committee of despair",
        "Once bitten",
        "Curves",
        "Atmosphere",
        "Beginner's luck",
        "Expert opinion",
        "Bittersweet",
        "The drawing board",
        "Sweetbitter",
        "Zero gravity",
        "Special delivery"
    )

    fun getRandomAlbums(count: Long): List<Album> = (1..count).map { index -> generateRandomAlbum(index.toInt()) }

    private fun generateRandomAlbum(index: Int) = Album(
        id = UUID.randomUUID().toString(),
        name = albumNames[index % albumNames.size],
        songs = SongMock.generateSongs(count = (5..40).random())
    )
}