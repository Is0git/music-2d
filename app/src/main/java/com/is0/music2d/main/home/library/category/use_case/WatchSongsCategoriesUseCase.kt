package com.is0.music2d.main.home.library.category.use_case

import com.is0.music2d.main.home.library.category.utils.data.presentation.SongsCategory
import com.is0.music2d.music.album.use_case.GetUserAlbumsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class WatchSongsCategoriesUseCase @Inject constructor(
    private val getUserAlbumsUseCase: GetUserAlbumsUseCase,
) {
    suspend fun watchSongCategories(): Flow<List<SongsCategory>> =
        getUserAlbumsUseCase.watchUserAlbums(5).map { albums -> albums.map { album -> SongsCategory.fromAlbum(album) } }
}