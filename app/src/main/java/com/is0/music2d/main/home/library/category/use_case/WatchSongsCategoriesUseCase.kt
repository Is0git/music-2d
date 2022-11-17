package com.is0.music2d.main.home.library.category.use_case

import com.is0.music2d.main.home.library.category.utils.data.presentation.SongsCategory
import com.is0.music2d.music.album.usecase.GetUserAlbumsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class WatchSongsCategoriesUseCase @Inject constructor(
    private val getUserAlbumsUseCase: GetUserAlbumsUseCase,
) {
    suspend fun watchSongCategoriesUseCase(): Flow<List<SongsCategory>> =
        getUserAlbumsUseCase.watchUserAlbums().map { albums -> albums.map(SongsCategory::fromAlbum) }
}