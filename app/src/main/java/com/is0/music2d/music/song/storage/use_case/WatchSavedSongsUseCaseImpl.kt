package com.is0.music2d.music.song.storage.use_case

import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.utils.data.database.data.repository.DatabaseSongsRepository
import com.is0.music2d.music.song.utils.data.domain.Song
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class WatchSavedSongsUseCaseImpl @Inject constructor(
    private val savedSongsRepository: SavedSongsRepository,
    private val databaseSongsRepository: DatabaseSongsRepository,
) : WatchSavedSongsUseCase {
    override fun watchSavedSongs(): Flow<List<Song>> = savedSongsRepository
        .watchSavedSongs().map { savedSongs ->
            databaseSongsRepository.getSongsByIds(savedSongs.map(SavedSong::songId))
        }
}