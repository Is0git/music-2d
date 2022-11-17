package com.is0.music2d.main.home.details.storage.memory.use_case

import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.music.song.storage.memory.repository.InMemorySongsRepository
import com.is0.music2d.music.song.utils.data.domain.Song
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ToggleInMemorySavedSongUseCase @Inject constructor(
    private val inMemorySongsRepository: InMemorySongsRepository,
) : ToggleSavedSongUseCase {
    override suspend fun toggleSavedSong(song: Song) {
        inMemorySongsRepository.toggleSavedSong(song)
    }
}