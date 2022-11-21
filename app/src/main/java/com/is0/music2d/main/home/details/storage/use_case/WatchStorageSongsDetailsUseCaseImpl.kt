package com.is0.music2d.main.home.details.storage.use_case

import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import com.is0.music2d.music.song.storage.utils.merge.SongsMergeResult
import com.is0.music2d.music.song.utils.data.database.repository.DatabaseSongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
open class WatchStorageSongsDetailsUseCaseImpl @Inject constructor(
    private val databaseSongsRepository: DatabaseSongsRepository,
    private val savedSongsRepository: SavedSongsRepository,
    private val savedSongsMerger: SavedSongsMerger,
) : WatchStorageSongsDetailsUseCase {
    override suspend fun watchStorageSongsDetails(): Flow<List<StorageDetailsSong>> {
        return combine(
            savedSongsRepository.watchSavedSongs(),
            databaseSongsRepository.watchSongs()
        ) { savedSongs, databaseSongs ->
            when (val mergeResult: SongsMergeResult = savedSongsMerger.mergeSavedSongs(databaseSongs, savedSongs)) {
                is SongsMergeResult.Merged -> mergeResult.songsWithStorageType.map { (song, songStorageTypes) ->
                    StorageDetailsSong(
                        song = song,
                        isSaved = songStorageTypes.isNotEmpty(),
                    )
                }
                else -> databaseSongs.map { song ->
                    StorageDetailsSong(
                        song = song,
                        isSaved = false,
                    )
                }
            }
        }
    }
}