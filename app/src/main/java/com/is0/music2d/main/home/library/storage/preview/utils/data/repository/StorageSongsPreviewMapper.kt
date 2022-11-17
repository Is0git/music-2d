package com.is0.music2d.main.home.library.storage.preview.utils.data.repository

import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview

interface StorageSongsPreviewMapper<T> {
    fun toStoragePreviewDomain(storageSongsPreviewEntity: T): StorageSongsPreview

    fun toStoragePreviewEntity(storageSongsPreview: StorageSongsPreview): T
}