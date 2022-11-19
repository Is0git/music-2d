package com.is0.music2d.music.song.storage.utils.data.domain

enum class SongStorageType {
    NONE, MEMORY, FILESYSTEM
}

fun SongStorageType.isSaved(): Boolean = this != SongStorageType.NONE