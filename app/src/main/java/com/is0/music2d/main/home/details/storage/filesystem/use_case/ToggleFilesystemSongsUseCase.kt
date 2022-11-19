package com.is0.music2d.main.home.details.storage.filesystem.use_case

import com.is0.music2d.main.home.details.storage.filesystem.utils.DatabaseSongsDetailsRepository
import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.music.song.utils.data.domain.Song
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ToggleFilesystemSongsUseCase @Inject constructor(
    private val databaseSongsDetailsRepository: DatabaseSongsDetailsRepository,
) : ToggleSavedSongUseCase {
    override suspend fun toggleSavedSong(songId: String) {
        databaseSongsDetailsRepository.toggleSavedSong(songId = songId)
    }
}