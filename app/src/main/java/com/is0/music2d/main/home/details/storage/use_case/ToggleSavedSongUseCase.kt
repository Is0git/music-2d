package com.is0.music2d.main.home.details.storage.use_case

interface ToggleSavedSongUseCase {
    suspend fun toggleSavedSong(songId: String)
}