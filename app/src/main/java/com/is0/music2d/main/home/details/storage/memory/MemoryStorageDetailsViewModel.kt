package com.is0.music2d.main.home.details.storage.memory

import com.is0.music2d.main.home.details.storage.StorageDetailsViewModel
import com.is0.music2d.main.home.details.storage.memory.use_case.GetInMemoryStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.memory.use_case.ToggleInMemorySavedSongUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoryStorageDetailsViewModel @Inject constructor(
    getInMemoryStorageSongsUseCase: GetInMemoryStorageSongsDetailsUseCase,
    toggleInMemorySavedSongUseCase: ToggleInMemorySavedSongUseCase,
) : StorageDetailsViewModel(
    getStorageSongsDetailsUseCase = getInMemoryStorageSongsUseCase,
    toggleSavedSongUseCase = toggleInMemorySavedSongUseCase,
)