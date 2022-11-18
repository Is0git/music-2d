package com.is0.music2d.music.song.storage.memory.repository.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.is0.music2d.music.song.storage.SongStorageType
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.domain.SongSize

@Entity
data class SongEntity(
    @PrimaryKey(autoGenerate = false)
    val songId: String,
    val albumId: String,
    val title: String,
    @Embedded
    val songSize: SongSize,
    @Embedded
    val artist: ArtistEntity,
    val durationMillis: Long,
    val imageUrl: String,
    val isSaved: Boolean,
)

fun SongEntity.toSong(): Song = Song(
    id = songId,
    songSize = songSize,
    title = title,
    artist = artist.toArtist(),
    durationMillis = durationMillis,
    imageUrl = imageUrl,
    songStorageType = SongStorageType.FILESYSTEM,
)

fun Song.toEntity(albumId: String): SongEntity = SongEntity(
    songId = id,
    songSize = songSize,
    title = title,
    artist = artist.toEntity(),
    durationMillis = durationMillis,
    imageUrl = imageUrl,
    albumId = albumId,
    isSaved = true,
)
