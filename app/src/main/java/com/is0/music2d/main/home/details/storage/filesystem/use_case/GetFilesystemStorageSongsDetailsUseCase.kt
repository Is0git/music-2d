package com.is0.music2d.main.home.details.storage.filesystem.use_case

import com.is0.music2d.main.home.details.storage.filesystem.utils.DatabaseSongsDetailsRepository
import com.is0.music2d.main.home.details.storage.use_case.GetStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class GetFilesystemStorageSongsDetailsUseCase @Inject constructor(
    private val databaseSongsDetailsRepository: DatabaseSongsDetailsRepository,
) : GetStorageSongsDetailsUseCase {
    override suspend fun getStorageSongs(): List<StorageDetailsSong> =
        databaseSongsDetailsRepository.getStorageDetailsSongs()
}