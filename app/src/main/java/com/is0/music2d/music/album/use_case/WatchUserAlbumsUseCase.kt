package com.is0.music2d.music.album.use_case

import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.memory.InMemoryAlbumsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class WatchUserAlbumsUseCase @Inject constructor(
    private val inMemoryAlbumsRepository: InMemoryAlbumsRepository,
) {
    suspend fun watchUserAlbums(
        count: Int = -1,
    ): Flow<List<Album>> = inMemoryAlbumsRepository.watchAlbums(count)
}