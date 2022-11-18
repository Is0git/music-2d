package com.is0.music2d.main.home.details.storage.memory.use_case

import com.is0.music2d.main.home.details.storage.memory.utils.InMemorySongsDetailsRepository
import com.is0.music2d.main.home.details.storage.use_case.GetStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetInMemoryStorageSongsDetailsUseCase @Inject constructor(
    private val inMemorySongsRepository: InMemorySongsDetailsRepository,
) : GetStorageSongsDetailsUseCase {
    override suspend fun getStorageSongs(): List<StorageDetailsSong> =
        inMemorySongsRepository.getStorageDetailsSongs()
}