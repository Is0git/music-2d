package com.is0.music2d.music.song.utils.data

import com.is0.music2d.music.song.utils.data.domain.Song
import kotlinx.coroutines.flow.Flow

interface SongsRepository {
    suspend fun watchSongs(): Flow<List<Song>>

    suspend fun addSongs(songs: List<Song>)

    suspend fun addSong(song: Song)
}
