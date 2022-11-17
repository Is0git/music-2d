package com.is0.music2d.music.album.utils.data.memory.mapper

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.memory.entity.InMemoryAlbum
import com.is0.music2d.music.song.utils.data.domain.Song
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryAlbumsMapper @Inject constructor() {
    fun toAlbumDomain(songs: List<Song>, inMemoryAlbums: List<InMemoryAlbum>): List<Album> {
        val songsMap: Map<String, Song> = songs.associateBy { songId -> songId.id }

        return inMemoryAlbums.map { memoryAlbum ->
            val domainSongs: List<Song> =
                memoryAlbum.songsIds
                    .filter { songId -> songsMap.contains(songId) }
                    .map { songId -> songsMap[songId]!! }
            Album(
                id = memoryAlbum.id,
                name = memoryAlbum.name,
                songs = domainSongs,
            )
        }
    }

    fun toMemoryAlbum(albums: List<Album>): List<InMemoryAlbum> = albums.map { album ->
        InMemoryAlbum(
            id = album.id,
            name = album.name,
            songsIds = album.songs.map { songs -> songs.id },
        )
    }
}