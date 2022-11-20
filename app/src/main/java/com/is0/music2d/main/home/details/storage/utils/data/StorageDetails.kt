package com.is0.music2d.main.home.details.storage.utils.data

import com.is0.music2d.main.home.details.album.data.DETAILS_HEADER_IMAGES_COUNT

data class StorageDetails(
    val songs: List<StorageDetailsSong> = emptyList(),
    val previewImages: List<String> = emptyList(),
    val totalDuration: Long = 0,
    val songCount: Int = 0,
)

// TODO(Daniel: Execute this in use case or repository)
fun List<StorageDetailsSong>.toStorageDetails(): StorageDetails {
    val savedSongs = this.filter { song -> song.isSaved }

    return StorageDetails(
        songs = this,
        previewImages = this.take(DETAILS_HEADER_IMAGES_COUNT).map { it.song.imageUrl },
        totalDuration = savedSongs.sumOf { detailsSong -> detailsSong.song.durationMillis },
        songCount = savedSongs.size,
    )
}