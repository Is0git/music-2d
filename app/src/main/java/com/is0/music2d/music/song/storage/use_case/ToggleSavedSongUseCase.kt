package com.is0.music2d.music.song.storage.use_case

import com.is0.music2d.main.home.details.storage.filesystem.use_case.ToggleFilesystemSongsUseCase
import com.is0.music2d.main.home.details.storage.memory.use_case.ToggleMemorySavedSongUseCase
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import dagger.hilt.android.scopes.ViewModelScoped
import timber.log.Timber
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
        Timber.d("Toggling song: $songId, type: $songStorageType")
        if (songStorageType == SongStorageType.FILESYSTEM) {
            toggleFilesystemSongsUseCase.toggleSavedSong(songId)
        } else if (songStorageType == SongStorageType.MEMORY) {
            toggleMemorySavedSongUseCase.toggleSavedSong(songId)
        }
    }
}