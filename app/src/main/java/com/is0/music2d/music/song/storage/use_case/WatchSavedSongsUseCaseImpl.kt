package com.is0.music2d.music.song.storage.use_case

import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.memory.repository.InMemorySongsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

open class WatchSavedSongsUseCaseImpl @Inject constructor(
    private val savedSongsRepository: SavedSongsRepository,
    private val inMemorySongsRepository: InMemorySongsRepository,
    private val songStorageType: SongStorageType,
) : WatchSavedSongsUseCase {
    override fun watchSavedSongs(): Flow<List<Song>> = savedSongsRepository
        .watchSavedSongs().map { savedSongs ->
            inMemorySongsRepository.getSongsByIds(savedSongs.map(SavedSong::songId))
        }.map { songs ->
            songs.map { song ->
                song.copy(songStorageType = songStorageType)
            }
        }
}