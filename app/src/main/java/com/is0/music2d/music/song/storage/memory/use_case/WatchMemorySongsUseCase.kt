package com.is0.music2d.music.song.storage.memory.use_case

import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.storage.use_case.WatchSavedSongsUseCaseImpl
import com.is0.music2d.music.song.utils.data.database.data.repository.DatabaseSongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WatchMemorySongsUseCase @Inject constructor(
    memorySongsRepository: MemorySongsRepository,
    databaseSongsRepository: DatabaseSongsRepository,
) : WatchSavedSongsUseCaseImpl(
    savedSongsRepository = memorySongsRepository,
    databaseSongsRepository = databaseSongsRepository,
)