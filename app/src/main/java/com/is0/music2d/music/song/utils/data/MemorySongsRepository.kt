package com.is0.music2d.music.song.utils.data

import com.is0.music2d.music.song.utils.data.domain.Song
import kotlinx.coroutines.flow.Flow

interface MemorySongsRepository {
    suspend fun watchSongs(): Flow<List<Song>>

    suspend fun addSongs(songs: List<Song>)

    suspend fun addSong(song: Song)

    suspend fun getSongs(): List<Song>

    suspend fun removeSong(songId: String)

    suspend fun toggleSavedSong(song: Song)

    suspend fun getSongsByIds(songIds: List<String>): List<Song>
}
