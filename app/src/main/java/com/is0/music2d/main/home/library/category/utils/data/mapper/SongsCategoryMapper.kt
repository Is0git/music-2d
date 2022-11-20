package com.is0.music2d.main.home.library.category.utils.data.mapper

import com.is0.music2d.main.home.library.category.utils.data.domain.SongsCategory
import com.is0.music2d.music.album.utils.data.domain.StoredSongsAlbum
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SongsCategoryMapper @Inject constructor() {
    fun toSongsCategory(
        storedSongsAlbum: StoredSongsAlbum,
    ): SongsCategory = SongsCategory(
        id = storedSongsAlbum.album.id,
        name = storedSongsAlbum.album.name,
        songs = storedSongsAlbum.storedSongs,
    )
}