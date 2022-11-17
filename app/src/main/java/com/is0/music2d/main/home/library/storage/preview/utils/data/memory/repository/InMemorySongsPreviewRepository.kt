package com.is0.music2d.main.home.library.storage.preview.utils.data.memory.repository

import com.is0.music2d.main.home.library.storage.preview.utils.data.memory.InMemorySongPreviewStore
import com.is0.music2d.main.home.library.storage.preview.utils.data.memory.entity.InMemorySongStoragePreview
import com.is0.music2d.main.home.library.storage.preview.utils.data.memory.mapper.InMemoryStorageSongsPreviewMapper
import com.is0.music2d.main.home.library.storage.preview.utils.data.repository.StorageSongsPreviewRepository
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class InMemorySongsPreviewRepository @Inject constructor(
    inMemorySongPreviewStore: InMemorySongPreviewStore,
    inMemoryStorageSongsPreviewMapper: InMemoryStorageSongsPreviewMapper,
) : StorageSongsPreviewRepository<InMemorySongStoragePreview>(
    storageSongPreviewStore = inMemorySongPreviewStore,
    storageSongsPreviewMapper = inMemoryStorageSongsPreviewMapper,
)