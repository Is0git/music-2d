package com.is0.music2d.main.home.details.album.use_case

import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.album.data.domain.toDetails
import com.is0.music2d.music.album.utils.data.domain.StoredSongsAlbum
import com.is0.music2d.music.song.storage.use_case.WatchAlbumWithStoredSongsUseCase
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ViewModelScoped
class WatchAlbumDetailsUseCase @Inject constructor(
    private val watchAlbumWithStoredSongsUseCase: WatchAlbumWithStoredSongsUseCase,
) {
    suspend fun watchAlbumDetails(albumId: String): Flow<AlbumDetails> =
        watchAlbumWithStoredSongsUseCase.watchAlbumWithStoredSongs(albumId)
            .map(StoredSongsAlbum::toDetails)
}