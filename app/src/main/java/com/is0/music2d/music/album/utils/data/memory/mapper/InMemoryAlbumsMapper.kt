package com.is0.music2d.music.album.utils.data.memory.mapper

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.memory.entity.InMemoryAlbum
import com.is0.music2d.music.song.storage.memory.mapper.InMemorySongsMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InMemoryAlbumsMapper @Inject constructor(
    private val inMemorySongsMapper: InMemorySongsMapper,
) {
    fun toAlbumDomain(inMemoryAlbum: InMemoryAlbum): Album = Album(
        id = inMemoryAlbum.id,
        name = inMemoryAlbum.name,
        songs = inMemoryAlbum.songs.map(inMemorySongsMapper::toSongDomain),
    )

    fun toMemoryAlbum(album: Album): InMemoryAlbum =
        InMemoryAlbum(
            id = album.id,
            name = album.name,
            songs = album.songs.map(inMemorySongsMapper::toSongEntity),
        )
}