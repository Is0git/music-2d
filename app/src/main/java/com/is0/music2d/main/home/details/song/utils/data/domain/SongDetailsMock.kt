package com.is0.music2d.main.home.details.song.utils.data.domain

import com.is0.music2d.music.song.utils.data.domain.SongSize
import com.is0.music2d.music.utils.data.domain.ArtistMock
import com.is0.music2d.utils.data.mock.ImageMock
import com.is0.music2d.utils.data.size.SizeUnit

object SongDetailsMock {
    val songDetails
        get() = SongDetails(
            id = "1",
            songTitle = "Cool About you",
            albumTitle = "Hello Angel",
            songSize = SongSize(
                quantity = 14.4f,
                unit = SizeUnit.MB,
            ),
            artist = ArtistMock.randomArtist,
            durationMillis = 354000,
            imageUrl = "https://i.scdn.co/image/ab67616d0000b2738120d2b107af14b1bf67cd06",
            creationDate = "2000-07-12",
        )
}