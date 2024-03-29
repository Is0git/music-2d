package com.is0.music2d.main.home.details.storage.filesystem.use_case

import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.music.song.storage.filesystem.utils.data.FilesystemSongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ToggleFilesystemSongsUseCase @Inject constructor(
    private val filesystemSongsRepository: FilesystemSongsRepository,
) : ToggleSavedSongUseCase {
    override suspend fun toggleSavedSong(songId: String) {
        filesystemSongsRepository.toggleSavedSong(songId = songId)
    }
}