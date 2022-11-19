package com.is0.music2d.main.home.details.storage.filesystem.use_case

import com.is0.music2d.main.home.details.storage.use_case.WatchStorageSongsDetailsUseCaseImpl
import com.is0.music2d.music.song.storage.filesystem.utils.data.FilesystemSongsRepository
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import com.is0.music2d.music.song.utils.data.memory.repository.InMemorySongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class WatchFilesystemSongsDetailsUseCase @Inject constructor(
    inMemorySongsRepository: InMemorySongsRepository,
    filesystemSongsRepository: FilesystemSongsRepository,
    savedSongsMerger: SavedSongsMerger,
) : WatchStorageSongsDetailsUseCaseImpl(
    inMemorySongsRepository = inMemorySongsRepository,
    savedSongsRepository = filesystemSongsRepository,
    savedSongsMerger = savedSongsMerger,
)