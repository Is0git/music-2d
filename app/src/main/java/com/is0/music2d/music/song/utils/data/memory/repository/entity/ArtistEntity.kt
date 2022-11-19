package com.is0.music2d.music.song.utils.data.memory.repository.entity

import com.is0.music2d.music.utils.data.domain.Artist

data class ArtistEntity(
    val id: String,
    val name: String,
    val artistImageUrl: String,
)

fun ArtistEntity.toArtist() = Artist(
    id = id,
    name = name,
    artistImageUrl = artistImageUrl,
)

fun Artist.toEntity() = ArtistEntity(
    id = id,
    name = name,
    artistImageUrl = artistImageUrl,
)