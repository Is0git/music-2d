package com.is0.music2d.music.use_case

import com.is0.music2d.music.album.utils.data.database.DatabaseAlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.AlbumMock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopulateMusicUseCase @Inject constructor(
    private val databaseAlbumsRepository: DatabaseAlbumsRepository,
) {
    suspend fun populateMusic(albumsCount: Long = 10) {
        if (!databaseAlbumsRepository.exists()) {
            val albums = AlbumMock.getRandomAlbums(albumsCount)

            databaseAlbumsRepository.addAlbums(albums)
        }
    }
}