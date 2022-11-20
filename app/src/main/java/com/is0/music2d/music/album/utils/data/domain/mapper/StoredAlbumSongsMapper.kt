package com.is0.music2d.music.album.utils.data.domain.mapper

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.domain.StoredSongsAlbum
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.utils.data.domain.Song
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class StoredAlbumSongsMapper @Inject constructor() {
    fun toStoredSongsAlbumMerged(
        songsWithStorageType: Map<Song, List<SongStorageType>>,
        album: Album,
    ): StoredSongsAlbum = StoredSongsAlbum(
        album = album,
        storedSongs = songsWithStorageType.map { (song, storageTypes) ->
            StoredSong(
                song = song,
                songStorageTypes = storageTypes,
            )
        }
    )

    fun toStoredAlbumSongs(
        album: Album,
    ): StoredSongsAlbum = StoredSongsAlbum(
        album = album,
        storedSongs = album.songs.map { song ->
            StoredSong(
                song = song,
                songStorageTypes = emptyList(),
            )
        }
    )
}