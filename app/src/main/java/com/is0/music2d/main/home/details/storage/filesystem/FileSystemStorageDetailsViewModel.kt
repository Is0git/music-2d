package com.is0.music2d.main.home.details.storage.filesystem

import com.is0.music2d.main.home.details.storage.StorageDetailsViewModel
import com.is0.music2d.main.home.details.storage.filesystem.use_case.GetFilesystemStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.filesystem.use_case.ToggleFilesystemSongsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FileSystemStorageDetailsViewModel @Inject constructor(
    getFilesystemStorageSongsUseCase: GetFilesystemStorageSongsDetailsUseCase,
    toggleFilesystemSongsUseCase: ToggleFilesystemSongsUseCase,
) : StorageDetailsViewModel(
    getStorageSongsDetailsUseCase = getFilesystemStorageSongsUseCase,
    toggleSavedSongUseCase = toggleFilesystemSongsUseCase,
)