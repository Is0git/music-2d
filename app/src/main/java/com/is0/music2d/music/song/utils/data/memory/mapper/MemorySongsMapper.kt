package com.is0.music2d.music.song.utils.data.memory.mapper

import com.is0.music2d.music.song.utils.data.memory.entity.MemorySong
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.mapper.SongsMapper
import javax.inject.Inject

class MemorySongsMapper @Inject constructor() : SongsMapper<MemorySong> {
    override fun toSongDomain(song: MemorySong): Song = Song(
        id = song.id,
        title = song.title,
        songSize = song.songSize,
        artist = song.artist,
        durationMillis = song.durationMillis,
        imageUrl = song.imageUrl,
    )

    override fun toSongEntity(song: Song): MemorySong = MemorySong(
        id = song.id,
        title = song.title,
        songSize = song.songSize,
        artist = song.artist,
        durationMillis = song.durationMillis,
        imageUrl = song.imageUrl,
    )
}