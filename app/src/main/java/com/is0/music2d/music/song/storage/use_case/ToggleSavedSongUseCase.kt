package com.is0.music2d.music.song.storage.use_case

import com.is0.music2d.main.home.details.storage.filesystem.use_case.ToggleFilesystemSongsUseCase
import com.is0.music2d.main.home.details.storage.memory.use_case.ToggleMemorySavedSongUseCase
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ToggleSavedSongUseCase @Inject constructor(
    private val toggleFilesystemSongsUseCase: ToggleFilesystemSongsUseCase,
    private val toggleMemorySavedSongUseCase: ToggleMemorySavedSongUseCase,
) {
    suspend fun toggleSavedSongUseCase(
        songId: String,
        songStorageType: SongStorageType,
    ) {
        if (songStorageType == SongStorageType.MEMORY) {
            toggleFilesystemSongsUseCase.toggleSavedSong(songId)
        } else if (songStorageType == SongStorageType.FILESYSTEM) {
            toggleMemorySavedSongUseCase.toggleSavedSong(songId)
        }
    }
}