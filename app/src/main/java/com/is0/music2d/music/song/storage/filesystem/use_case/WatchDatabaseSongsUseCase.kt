package com.is0.music2d.music.song.storage.filesystem.use_case

import com.is0.music2d.music.song.storage.use_case.WatchSavedSongsUseCase
import com.is0.music2d.music.song.utils.data.domain.Song

class WatchDatabaseSongsUseCase : WatchSavedSongsUseCase {
    override fun watchSavedSongs(): List<Song> {
        return emptyList()
    }
}