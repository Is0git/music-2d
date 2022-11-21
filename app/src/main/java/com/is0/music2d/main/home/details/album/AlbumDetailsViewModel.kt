@file:OptIn(FlowPreview::class)

package com.is0.music2d.main.home.details.album

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.MainGraph
import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.album.use_case.WatchAlbumDetailsUseCase
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val watchAlbumDetailsUseCase: WatchAlbumDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private val albumId: String get() = savedStateHandle.get<String>(MainGraph.AlbumDetails.ALBUM_ID)!!

    val albumDetails = createMutableLiveData(AlbumDetails.empty())

    val availableSongStorageTypes: List<SongStorageType> = allSongStorageTypes()

    init {
        watchAlbumDetails()
    }

    private fun watchAlbumDetails() {
        flow { emit(albumId) }
            .flatMapConcat { albumId ->
                watchAlbumDetailsUseCase.watchAlbumDetails(albumId = albumId)
                    .withStateHandler()
                    .onEach { albumDetails -> this.albumDetails.postValue(albumDetails) }
            }
            .launchIn(viewModelScope)
    }
}