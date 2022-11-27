package com.is0.music2d.main.home.library.storage_preview.use_case.filesystem

import com.is0.music2d.main.home.library.storage_preview.use_case.WatchStorageSongsPreviewUseCaseImpl
import com.is0.music2d.music.song.storage.filesystem.utils.data.FilesystemSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.database.repository.DatabaseSongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WatchFilesystemSongsPreviewUseCase @Inject constructor(
    databaseSongsRepository: DatabaseSongsRepository,
    filesystemSongsRepository: FilesystemSongsRepository,
) : WatchStorageSongsPreviewUseCaseImpl(
    databaseSongsRepository = databaseSongsRepository,
    savedSongsRepository = filesystemSongsRepository,
    songStorageType = SongStorageType.FILESYSTEM,
)