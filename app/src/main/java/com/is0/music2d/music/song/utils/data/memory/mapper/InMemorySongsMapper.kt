package com.is0.music2d.music.song.utils.data.memory.mapper

import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.memory.entity.InMemorySong
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.mapper.SongsMapper
import javax.inject.Inject

class InMemorySongsMapper @Inject constructor() : SongsMapper<InMemorySong> {
    override fun toSongDomain(song: InMemorySong): Song = Song(
        id = song.id,
        title = song.title,
        songSize = song.songSize,
        artist = song.artist,
        durationMillis = song.durationMillis,
        imageUrl = song.imageUrl,
    )

    override fun toSongEntity(song: Song): InMemorySong = InMemorySong(
        id = song.id,
        title = song.title,
        songSize = song.songSize,
        artist = song.artist,
        durationMillis = song.durationMillis,
        imageUrl = song.imageUrl,
    )
}