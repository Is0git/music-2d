package com.is0.music2d.main.home.library.storage_preview.use_case

import com.is0.music2d.main.home.library.storage_preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.database.repository.DatabaseSongsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
open class WatchStorageSongsPreviewUseCaseImpl @Inject constructor(
    private val databaseSongsRepository: DatabaseSongsRepository,
    private val savedSongsRepository: SavedSongsRepository,
    private val songStorageType: SongStorageType,
) : WatchStorageSongsPreviewUseCase {
    override fun watchStorageSongsPreview(): Flow<StorageSongsPreview> =
        combine(
            databaseSongsRepository.watchSongs(),
            savedSongsRepository.watchSavedSongs()
        ) { databaseSongs, memorySongs ->
            databaseSongs.filter { databaseSong ->
                memorySongs.any { memorySong -> databaseSong.id == memorySong.songId }
            }.sumOf { song -> song.durationMillis }.let { totalSongsDuration ->
                StorageSongsPreview(
                    songStorageType,
                    totalSongsDuration,
                )
            }
        }
}