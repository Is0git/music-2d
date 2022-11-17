package com.is0.music2d.main.home.details.utils.data.memory

import com.is0.music2d.main.home.details.utils.data.domain.AlbumDetails
import com.is0.music2d.music.album.utils.data.memory.entity.InMemoryAlbum
import com.is0.music2d.music.song.storage.memory.mapper.InMemorySongsMapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class AlbumDetailsInMemoryMapper @Inject constructor(
    private val inMemorySongsMapper: InMemorySongsMapper,
) {
    fun toAlbumDetails(inMemoryAlbum: InMemoryAlbum): AlbumDetails = AlbumDetails(
        name = inMemoryAlbum.name,
        totalDuration = inMemoryAlbum.songs.sumOf { it.durationMillis },
        songs = inMemoryAlbum.songs.map(inMemorySongsMapper::toSongDomain),
    )
}