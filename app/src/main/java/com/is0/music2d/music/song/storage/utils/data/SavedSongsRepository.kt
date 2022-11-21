package com.is0.music2d.music.song.storage.utils.data

import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import kotlinx.coroutines.flow.Flow

interface SavedSongsRepository {
    fun watchSavedSongs(): Flow<List<SavedSong>>

    fun watchCount(): Flow<Int>

    fun watchSongsByIds(songsIds: List<String>): Flow<List<SavedSong>>

    suspend fun addSavedSong(savedSong: SavedSong)

    suspend fun toggleSavedSong(songId: String)
}