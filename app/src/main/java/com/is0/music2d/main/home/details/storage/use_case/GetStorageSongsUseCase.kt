package com.is0.music2d.main.home.details.storage.use_case

import com.is0.music2d.music.song.utils.data.domain.Song
import kotlinx.coroutines.flow.Flow

interface GetStorageSongsUseCase {
    suspend fun getStorageSongs(): List<Song>
}