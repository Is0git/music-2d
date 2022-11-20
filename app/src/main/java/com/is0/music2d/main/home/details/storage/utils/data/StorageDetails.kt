package com.is0.music2d.main.home.details.storage.utils.data

import com.is0.music2d.main.home.details.album.data.DETAILS_HEADER_IMAGES_COUNT

data class StorageDetails(
    val songs: List<StorageDetailsSong> = emptyList(),
    val previewImages: List<String> = emptyList(),
    val totalDuration: Long = 0,
    val songCount: Int = 0,
)

fun List<StorageDetailsSong>.toStorageDetails() =
    StorageDetails(
        songs = this,
        previewImages = this.take(DETAILS_HEADER_IMAGES_COUNT).map { it.song.imageUrl },
        totalDuration = this.sumOf { detailsSong -> detailsSong.song.durationMillis },
        songCount = this.size,
    )