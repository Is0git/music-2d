package com.is0.music2d.main.home.details.storage.memory.use_case

import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ToggleMemorySavedSongUseCase @Inject constructor(
    private val memorySongsRepository: MemorySongsRepository,
) : ToggleSavedSongUseCase {
    override suspend fun toggleSavedSong(
        songId: String,
    ) = memorySongsRepository.toggleSavedSong(songId)
}