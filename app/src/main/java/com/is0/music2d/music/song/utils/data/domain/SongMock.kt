package com.is0.music2d.music.song.utils.data.domain

import com.is0.music2d.music.utils.data.domain.ArtistMock
import com.is0.music2d.utils.data.mock.ImageMock
import com.is0.music2d.utils.data.size.SizeUnit
import java.util.UUID

object SongMock {
    private val songTitles = listOf(
        "Thrill Of Infinity",
        "Times Of Time",
        "Violence Of His Enemies",
        "Love For My Life",
        "Faded And Life",
        "Broken And Money",
        "What's Up?",
        "Babe, We've Only Just Begun",
        "He Loves We Won't Stop",
        "I Know You're Bad For Me",
        "He Did It Like That",
        "I Love He Lost His Mind",
        "Jungle For The Broken",
        "Time For The Broken",
        "Violence Of Body",
        "Streets Of That Story",
        "Risky And Dreams",
        "No And Danger",
        "Babe, Shake Yo' Body",
        "She Said She Did It",
        "I'm Makin' Money",
        "I Heard He's Guilty",
        "She Thinks I'm A Thug",
        "I Know He Lost His Mind"
    )

    private const val TWO_MINUTES_MILLIS: Long = 120000
    private const val SIX_MINUTES_MILLIS: Long = 360000
    private val randomSongDurationRangeMillis = (TWO_MINUTES_MILLIS..SIX_MINUTES_MILLIS).random()

    private val images = listOf(
        "https://i.scdn.co/image/ab6761610000e5eb68f6e5892075d7f22615bd17",
        "https://www.bworldonline.com/wp-content/uploads/2021/11/adele-640x697.jpg",
        "https://i.scdn.co/image/ab67706f0000000372da819e1e97d6e78e767567",
        "https://i.scdn.co/image/ab67706f0000000372da819e1e97d6e78e767567",
        "https://i.scdn.co/image/ab67616d0000b273dea510881ec1e506485303e4",
        "https://i.scdn.co/image/ab67616d0000b273f907de96b9a4fbc04accc0d5",
        "https://i.scdn.co/image/ab6761610000e5eb4293385d324db8558179afd9",
        "https://i.scdn.co/image/ab67706f0000000387bff188c40608c48b82068f",
        "https://i.scdn.co/image/ab67706f00000003a231f671c289555cfd09f716",
        "https://i.scdn.co/image/ab67706f00000003a231f671c289555cfd09f716",
        "https://i.scdn.co/image/ab67616d0000b273f864bcdcc245f06831d17ae0",
        "https://i.scdn.co/image/ab67616d0000b2734e0362c225863f6ae2432651",
        "https://i.scdn.co/image/ab67616d0000b2739478c87599550dd73bfa7e02",
        "https://i.scdn.co/image/ab67706c0000bebb4b3d97518ca3e90c0ebcad3c",
        "https://i.scdn.co/image/ab67616d0000b273d363c475bf36a4d171913449",
        "https://i.scdn.co/image/ab67616d0000b273b1c4b76e23414c9f20242268",
        "https://i.scdn.co/image/ab67616d0000b273cb833e941e1ba35590da5c6b"
    )

    private val randomSongSize: SongSize
        get() {
            val min = 4.20f
            val max = 68f
            return SongSize(
                quantity = min + Math.random().toFloat() * (max - min),
                unit = SizeUnit.MB
            )
        }

    fun generateSongs(count: Int = 5): List<Song> = (1..count).map { index -> generateRandomSong(index) }

    fun generateRandomSong(index: Int = 1) = Song(
        id = UUID.randomUUID().toString(),
        title = songTitles.random(),
        songSize = randomSongSize,
        artist = ArtistMock.randomArtist,
        durationMillis = randomSongDurationRangeMillis,
        imageUrl = images.random(),
    )
}