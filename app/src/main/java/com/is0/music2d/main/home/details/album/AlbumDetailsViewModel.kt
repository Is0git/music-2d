package com.is0.music2d.main.home.details.album

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.MainGraph
import com.is0.music2d.main.home.details.album.use_case.WatchAlbumDetailsUseCase
import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.music.song.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val watchAlbumDetailsUseCase: WatchAlbumDetailsUseCase,
    private val toggleSavedSongUseCase: ToggleSavedSongUseCase,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private val albumId get() = savedStateHandle.get<String>(MainGraph.AlbumDetails.ALBUM_ID)!!

    val albumDetails = createMutableLiveData(AlbumDetails.empty())

    val availableSongStorageTypes: List<SongStorageType> = allSongStorageTypes()

    init {
        viewModelScope.launch {
            runCatching { watchAlbumDetails(albumId) }
                .onFailure(::setError)
        }
    }

    private suspend fun watchAlbumDetails(albumId: String) {
        watchAlbumDetailsUseCase.watchAlbumDetails(albumId = albumId)
            .catch { error -> setError(error) }
            .collect { albumDetails ->
                this.albumDetails.setValue(albumDetails)
            }
    }

    fun toggleSavedSong(
        songId: String,
        songStorageType: SongStorageType,
    ) {
        viewModelScope.launch {
            runCatching {
                isLoading.postValue(true)

                toggleSavedSongUseCase.toggleSavedSongUseCase(
                    songId = songId,
                    songStorageType = songStorageType,
                )
            }
                .onFailure { error -> setError(error) }

            isLoading.postValue(false)
        }
    }
}