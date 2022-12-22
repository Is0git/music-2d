package com.is0.music2d.main.home.details.song.use_case

import com.is0.music2d.main.home.details.song.utils.data.domain.SongDetails
import com.is0.music2d.main.home.details.song.utils.data.domain.toSongDetails
import com.is0.music2d.music.song.utils.data.database.repository.DatabaseSongsRepository
import com.is0.music2d.music.song.utils.data.domain.Song
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class WatchSongDetailsUseCase @Inject constructor(
    private val songsRepository: DatabaseSongsRepository
) {
    fun watchSongDetails(
        songId: String,
    ): Flow<SongDetails> = songsRepository
        .watchSong(songId)
        .map(Song::toSongDetails)
}