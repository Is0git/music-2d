package com.is0.music2d.music.song.storage.memory.utils.data.entity

import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType

data class MemorySongEntity(
    val songId: String,
)

fun MemorySongEntity.toDomain() = SavedSong(
    songId = songId,
    songStorageType = SongStorageType.MEMORY,
)

fun SavedSong.toMemorySongEntity() = MemorySongEntity(songId = songId)