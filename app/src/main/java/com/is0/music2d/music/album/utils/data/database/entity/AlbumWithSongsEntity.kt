package com.is0.music2d.music.album.utils.data.database.entity

import androidx.room.Embedded
import androidx.room.Relation
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.storage.memory.repository.entity.SongEntity
import com.is0.music2d.music.song.storage.memory.repository.entity.toSong

data class AlbumWithSongsEntity(
    @Embedded
    val album: AlbumEntity,
    @Relation(
        parentColumn = "albumId",
        entityColumn = "albumId"
    )
    val songs: List<SongEntity>,
)

fun AlbumWithSongsEntity.toAlbum(): Album = Album(
    id = this.album.albumId,
    name = this.album.name,
    songs = this.songs.map { songsEntity -> songsEntity.toSong() },
)