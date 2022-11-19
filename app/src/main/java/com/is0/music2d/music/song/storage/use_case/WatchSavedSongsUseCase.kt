package com.is0.music2d.music.song.storage.use_case

import com.is0.music2d.music.song.utils.data.domain.Song

interface WatchSavedSongsUseCase {
    fun watchSavedSongs(): List<Song>
}