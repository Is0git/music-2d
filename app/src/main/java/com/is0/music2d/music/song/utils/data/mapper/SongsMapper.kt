package com.is0.music2d.music.song.utils.data.mapper

import com.is0.music2d.music.song.utils.data.domain.Song

interface SongsMapper<T> {
    fun toSongDomain(song: T): Song

    fun toSongEntity(song: Song): T
}