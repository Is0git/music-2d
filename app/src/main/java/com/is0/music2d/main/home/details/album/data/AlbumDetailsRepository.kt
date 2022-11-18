package com.is0.music2d.main.home.details.album.data

import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import kotlinx.coroutines.flow.Flow

interface AlbumDetailsRepository {
    suspend fun watchAlbumDetails(albumId: String): Flow<AlbumDetails?>
}