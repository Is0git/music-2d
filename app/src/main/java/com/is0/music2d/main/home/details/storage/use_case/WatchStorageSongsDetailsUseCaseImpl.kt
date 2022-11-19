package com.is0.music2d.main.home.details.storage.use_case

import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.isSaved
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import com.is0.music2d.music.song.storage.utils.merge.SongsMergeResult
import com.is0.music2d.music.song.utils.data.memory.repository.InMemorySongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
open class WatchStorageSongsDetailsUseCaseImpl @Inject constructor(
    private val inMemorySongsRepository: InMemorySongsRepository,
    private val savedSongsRepository: SavedSongsRepository,
    private val savedSongsMerger: SavedSongsMerger,
) : WatchStorageSongsDetailsUseCase {
    override suspend fun watchStorageSongsDetails(): Flow<List<StorageDetailsSong>> {
        return combine(
            savedSongsRepository.watchSavedSongs(),
            inMemorySongsRepository.watchSongs()
        ) { savedSongs, inMemorySongs ->
            when (val mergeResult: SongsMergeResult = savedSongsMerger.mergeSavedSongs(inMemorySongs, savedSongs)) {
                is SongsMergeResult.Merged -> {
                    mergeResult.songsWithStorageType.map { songStorageTypePair ->
                        StorageDetailsSong(
                            song = songStorageTypePair.first,
                            isSaved = songStorageTypePair.second.isSaved(),
                        )
                    }
                }
                else -> inMemorySongs.map { song ->
                    StorageDetailsSong(
                        song = song,
                        isSaved = false,
                    )
                }
            }
        }
    }
}