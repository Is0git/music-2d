package com.is0.music2d.music.use_case

import com.is0.music2d.music.album.utils.data.domain.AlbumMock
import com.is0.music2d.music.album.utils.data.memory.InMemoryAlbumsRepository
import com.is0.music2d.music.song.utils.data.memory.repository.InMemorySongsRepository
import com.is0.music2d.music.song.utils.data.domain.Song
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopulateMusicUseCase @Inject constructor(
    private val albumsRepository: InMemoryAlbumsRepository,
    private val songsRepository: InMemorySongsRepository,
) {
    suspend fun populateMusic(albumsCount: Long = 10) {
        coroutineScope {
            launch {
                val albums = AlbumMock.getRandomAlbums(albumsCount)

                val songs: List<Song> = albums.map { album -> album.songs }.flatten()

                albumsRepository.addAlbums(albums)

                songsRepository.addSongs(songs)
            }
        }
    }
}