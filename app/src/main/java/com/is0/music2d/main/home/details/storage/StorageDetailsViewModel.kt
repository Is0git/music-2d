package com.is0.music2d.main.home.details.storage

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.main.home.details.storage.use_case.WatchStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.utils.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

open class StorageDetailsViewModel(
    private val watchStorageSongsDetailsUseCase: WatchStorageSongsDetailsUseCase,
    private val toggleSavedSongUseCase: ToggleSavedSongUseCase,
) : BaseViewModel() {
    val storageSongs = createMutableLiveData<List<StorageDetailsSong>?>(null)

    init {
        viewModelScope.launch {
            watchStorageSongs()
        }
    }

    private suspend fun watchStorageSongs() {
        runCatching {
            isLoading.postValue(true)

            watchStorageSongsDetailsUseCase.watchStorageSongsDetails()
                .catch { error -> setError(error) }
                .collect { storageDetailsSongs ->
                    if (storageSongs.value == null) {
                        isLoading.postValue(false)
                    }
                    storageSongs.postValue(storageDetailsSongs)
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