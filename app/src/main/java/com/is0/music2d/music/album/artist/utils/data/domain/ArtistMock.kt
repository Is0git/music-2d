package com.is0.music2d.music.album.artist.utils.data.domain

import com.is0.music2d.utils.data.mock.ImageMock
import java.util.UUID

object ArtistMock {
    private val artistNames = listOf(
        "Drake",
        "Lil Uzi",
        "Post Malone",
        "Travis Scott",
        "Linkin Park",
        "Coldplay",
        "Sum 21",
    )

    val randomArtist
        get() = Artist(
            id = UUID.randomUUID().toString(),
            name = artistNames.random(),
            artistImageUrl = ImageMock.randomImage
        )
}