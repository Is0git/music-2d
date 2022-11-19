package com.is0.music2d.music.song.storage.memory.use_case

import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.storage.use_case.WatchSavedSongsUseCaseImpl
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.memory.repository.InMemorySongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WatchMemorySongsUseCase @Inject constructor(
    memorySongsRepository: MemorySongsRepository,
    inMemorySongsRepository: InMemorySongsRepository,
) : WatchSavedSongsUseCaseImpl(
    inMemorySongsRepository = inMemorySongsRepository,
    savedSongsRepository = memorySongsRepository,
    songStorageType = SongStorageType.MEMORY,
)