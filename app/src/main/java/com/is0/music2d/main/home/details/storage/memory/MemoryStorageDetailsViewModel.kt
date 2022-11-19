package com.is0.music2d.main.home.details.storage.memory

import com.is0.music2d.main.home.details.storage.StorageDetailsViewModel
import com.is0.music2d.main.home.details.storage.filesystem.use_case.WatchFilesystemSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.memory.use_case.ToggleInMemorySavedSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoryStorageDetailsViewModel @Inject constructor(
    getInMemoryStorageSongsUseCase: WatchFilesystemSongsDetailsUseCase,
    toggleInMemorySavedSongUseCase: ToggleInMemorySavedSongUseCase,
) : StorageDetailsViewModel(
    watchStorageSongsDetailsUseCase = getInMemoryStorageSongsUseCase,
    toggleSavedSongUseCase = toggleInMemorySavedSongUseCase,
)