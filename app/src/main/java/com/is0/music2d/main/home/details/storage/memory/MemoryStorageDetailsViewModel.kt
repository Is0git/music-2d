package com.is0.music2d.main.home.details.storage.memory

import com.is0.music2d.main.home.details.storage.StorageDetailsViewModel
import com.is0.music2d.main.home.details.storage.memory.use_case.ToggleMemorySavedSongUseCase
import com.is0.music2d.main.home.details.storage.memory.use_case.WatchMemorySongsDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MemoryStorageDetailsViewModel @Inject constructor(
    watchMemorySongsDetailsUseCase: WatchMemorySongsDetailsUseCase,
    toggleMemorySavedSongUseCase: ToggleMemorySavedSongUseCase,
) : StorageDetailsViewModel(
    watchStorageSongsDetailsUseCase = watchMemorySongsDetailsUseCase,
    toggleSavedSongUseCase = toggleMemorySavedSongUseCase,
)