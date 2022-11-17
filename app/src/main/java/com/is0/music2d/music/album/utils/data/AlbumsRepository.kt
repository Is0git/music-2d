package com.is0.music2d.music.album.utils.data

import com.is0.music2d.music.album.utils.data.domain.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    suspend fun watchAlbums(): Flow<List<Album>>

    suspend fun addAlbums(albums: List<Album>)

    suspend fun addSongToAlbum(albumId: String, songId: String)

}