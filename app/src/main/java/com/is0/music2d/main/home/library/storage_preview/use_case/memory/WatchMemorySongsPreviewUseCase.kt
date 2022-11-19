package com.is0.music2d.main.home.library.storage_preview.use_case.memory

import com.is0.music2d.main.home.library.storage_preview.use_case.WatchStorageSongsPreviewUseCaseImpl
import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.database.data.repository.DatabaseSongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WatchMemorySongsPreviewUseCase @Inject constructor(
    databaseSongsRepository: DatabaseSongsRepository,
    memorySongsRepository: MemorySongsRepository,
) : WatchStorageSongsPreviewUseCaseImpl(
    databaseSongsRepository = databaseSongsRepository,
    savedSongsRepository = memorySongsRepository,
    songStorageType = SongStorageType.MEMORY,
)