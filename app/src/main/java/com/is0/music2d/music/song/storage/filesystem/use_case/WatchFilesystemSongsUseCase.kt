package com.is0.music2d.music.song.storage.filesystem.use_case

import com.is0.music2d.music.song.storage.filesystem.utils.data.FilesystemSongsRepository
import com.is0.music2d.music.song.storage.use_case.WatchSavedSongsUseCaseImpl
import com.is0.music2d.music.song.utils.data.database.data.repository.DatabaseSongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WatchFilesystemSongsUseCase @Inject constructor(
    filesystemSongsRepository: FilesystemSongsRepository,
    inMemorySongsRepository: DatabaseSongsRepository,
) : WatchSavedSongsUseCaseImpl(
    databaseSongsRepository = inMemorySongsRepository,
    savedSongsRepository = filesystemSongsRepository,
)