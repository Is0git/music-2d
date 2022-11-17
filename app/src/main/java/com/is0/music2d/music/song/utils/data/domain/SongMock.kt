package com.is0.music2d.music.song.utils.data.domain

import com.is0.music2d.music.album.artist.utils.data.domain.ArtistMock
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
        imageUrl = ImageMock.getRandomImageById(index),
    )
}