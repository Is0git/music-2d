package com.is0.music2d.music.album.utils.data.domain

import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong

data class StoredSongsAlbum(
    val album: Album,
    val storedSongs: List<StoredSong>,
)