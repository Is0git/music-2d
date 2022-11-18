package com.is0.music2d.music.album.utils.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.is0.music2d.music.album.utils.data.domain.Album

@Entity
data class AlbumEntity(
    @PrimaryKey(autoGenerate = false)
    val albumId: String,
    val name: String,
)

fun Album.toEntity(): AlbumEntity = AlbumEntity(
    albumId = id,
    name = name,
)