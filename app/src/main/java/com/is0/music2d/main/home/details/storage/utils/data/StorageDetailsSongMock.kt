package com.is0.music2d.main.home.details.storage.utils.data

import com.is0.music2d.music.song.utils.data.domain.SongMock

object StorageDetailsSongMock {
    val songs: List<StorageDetailsSong>
        get() = SongMock.generateSongs(15).map { song ->
            StorageDetailsSong(song.id, song, false)
        }
}