package com.is0.music2d.main.home.details.storage.memory.use_case

import com.is0.music2d.main.home.details.storage.use_case.GetStorageSongsUseCase
import com.is0.music2d.music.song.storage.memory.repository.InMemorySongsRepository
import com.is0.music2d.music.song.utils.data.domain.Song
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class GetInMemoryStorageSongsUseCase @Inject constructor(
    private val inMemorySongsRepository: InMemorySongsRepository,
) : GetStorageSongsUseCase {
    override suspend fun getStorageSongs(): List<Song> = inMemorySongsRepository.getSongs()
}