package com.is0.music2d.music.album.utils.data

import com.is0.music2d.music.album.utils.data.domain.Album
import kotlinx.coroutines.flow.Flow

interface AlbumsRepository {
    fun watchAlbums(count: Int): Flow<List<Album>>

    fun watchAlbum(albumId: String): Flow<Album>

    suspend fun addAlbums(albums: List<Album>)

    suspend fun exists(): Boolean
}