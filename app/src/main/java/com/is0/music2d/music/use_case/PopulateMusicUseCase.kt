package com.is0.music2d.music.use_case

import com.is0.music2d.music.album.utils.data.database.DatabaseAlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.AlbumMock
import com.is0.music2d.use_case.CheckDatabaseExistsUseCase
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopulateMusicUseCase @Inject constructor(
    private val databaseAlbumsRepository: DatabaseAlbumsRepository,
    private val checkDatabaseExistsUseCase: CheckDatabaseExistsUseCase,
) {
    suspend fun populateMusic(albumsCount: Long = 10) {
        coroutineScope {
            launch {
                if (!checkDatabaseExistsUseCase.exists()) {
                    val albums = AlbumMock.getRandomAlbums(albumsCount)

                    databaseAlbumsRepository.addAlbums(albums)
                }
            }
        }
    }
}