package com.is0.music2d.main.home.details.storage

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.details.album.data.DETAILS_HEADER_IMAGES_COUNT
import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.main.home.details.storage.use_case.WatchStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetails
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.utils.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

open class StorageDetailsViewModel(
    private val watchStorageSongsDetailsUseCase: WatchStorageSongsDetailsUseCase,
    private val toggleSavedSongUseCase: ToggleSavedSongUseCase,
) : BaseViewModel() {
    val storageDetails = createMutableLiveData<StorageDetails?>(null)

    init {
        viewModelScope.launch {
            watchStorageSongs()
        }
    }

    suspend fun watchStorageSongs() {
        runCatching {
            isLoading.postValue(true)

            watchStorageSongsDetailsUseCase.watchStorageSongsDetails()
                .catch { error -> setError(error) }
                .collect { storageDetailsSongs: List<StorageDetailsSong> ->
                    if (storageDetails.value == null) {
                        isLoading.postValue(false)
                    }
                    storageDetails.postValue(
                        StorageDetails(
                            songs = storageDetailsSongs,
                            previewImages = storageDetailsSongs.take(DETAILS_HEADER_IMAGES_COUNT).map {
                                it.song.imageUrl
                            }
                        )
                    )
                }
        }
    }

    fun toggleSavedSong(songId: String) {
        viewModelScope.launch {
            runCatching {
                isLoading.postValue(true)

                toggleSavedSongUseCase.toggleSavedSong(songId)
            }.onFailure { error -> setError(error) }

            isLoading.postValue(false)
        }
    }
}