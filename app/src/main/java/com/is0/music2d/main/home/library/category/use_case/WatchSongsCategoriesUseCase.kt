package com.is0.music2d.main.home.library.category.use_case

import com.is0.music2d.main.home.library.category.utils.data.domain.SongsCategory
import com.is0.music2d.main.home.library.category.utils.data.mapper.SongsCategoryMapper
import com.is0.music2d.music.song.storage.use_case.WatchAlbumsWithStoredSongsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class WatchSongsCategoriesUseCase @Inject constructor(
    private val watchAlbumsWithStoredSongsUseCase: WatchAlbumsWithStoredSongsUseCase,
    private val songsCategoryMapper: SongsCategoryMapper,
) {
    fun watchSongsCategories(count: Int): Flow<List<SongsCategory>> =
        watchAlbumsWithStoredSongsUseCase.watchAlbumsWithStoredSongs(count).map { storedSongsAlbums ->
            storedSongsAlbums.map { storedSongsAlbum ->
                songsCategoryMapper.toSongsCategory(storedSongsAlbum)
            }
        }
}