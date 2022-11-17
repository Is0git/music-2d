package com.is0.music2d.music.use_case

import com.is0.music2d.music.album.utils.data.domain.AlbumMock
import com.is0.music2d.music.album.utils.data.memory.InMemoryAlbumsRepository
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.storage.memory.repository.InMemorySongsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopulateMemoryMusicUseCase @Inject constructor(
    private val albumsRepository: InMemoryAlbumsRepository,
    private val songsRepository: InMemorySongsRepository,
) {
    suspend fun populateMusic() {
        val albums = AlbumMock.getRandomAlbums(10)

        val songs: List<Song> = albums.map { album -> album.songs }.flatten()

        albumsRepository.addAlbums(albums)

        songsRepository.addSongs(songs)
    }
}