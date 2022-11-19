package com.is0.music2d.main.home.details.storage.memory.use_case

import com.is0.music2d.main.home.details.storage.use_case.WatchStorageSongsDetailsUseCaseImpl
import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import com.is0.music2d.music.song.utils.data.memory.repository.InMemorySongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WatchMemorySongsDetailsUseCase @Inject constructor(
    inMemorySongsRepository: InMemorySongsRepository,
    memorySongsRepository: MemorySongsRepository,
    savedSongsMerger: SavedSongsMerger,
) : WatchStorageSongsDetailsUseCaseImpl(
    inMemorySongsRepository = inMemorySongsRepository,
    savedSongsRepository = memorySongsRepository,
    savedSongsMerger = savedSongsMerger,
)