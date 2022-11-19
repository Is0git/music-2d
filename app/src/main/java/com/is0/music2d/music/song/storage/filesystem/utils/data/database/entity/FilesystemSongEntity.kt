package com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType

@Entity
data class FilesystemSongEntity(
    @PrimaryKey(autoGenerate = false)
    val songId: String,
)

fun FilesystemSongEntity.toDomain() = SavedSong(
    songId = songId,
    songStorageType = SongStorageType.FILESYSTEM,
)

fun SavedSong.toFilesystemSongEntity() = FilesystemSongEntity(songId)