package com.is0.music2d.main.home.details.storage

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.details.storage.use_case.GetStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.utils.viewmodel.BaseViewModel
import kotlinx.collections.immutable.mutate
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import timber.log.Timber

open class StorageDetailsViewModel(
    private val getStorageSongsDetailsUseCase: GetStorageSongsDetailsUseCase,
    private val toggleSavedSongUseCase: ToggleSavedSongUseCase,
) : BaseViewModel() {
    val storageSongs = createMutableLiveData<List<StorageDetailsSong>?>(null)

    init {
        viewModelScope.launch {
            getStorageSongs()
        }
    }

    private suspend fun getStorageSongs() {
        runCatching {
            isLoading.postValue(true)

            getStorageSongsDetailsUseCase.getStorageSongs()
        }
            .onFailure { error -> setError(error) }
            .onSuccess { storageDetailsSongs ->
                storageSongs.postValue(storageDetailsSongs)
            }

        isLoading.postValue(false)
    }

    fun toggleSavedSong(songId: String, isSaved: Boolean) {
        viewModelScope.launch {
            runCatching {
                isLoading.postValue(true)

                val songs = storageSongs.value
                if (songs.isNullOrEmpty()) {
                    return@runCatching
                }

                val toggledSongIndex = songs.indexOfFirst { song -> song.id == songId }

                if (toggledSongIndex == -1) {
                    Timber.e("Toggled saved song not was not found")
                    return@runCatching
                }

                updateToggledSong(
                    songs = songs,
                    toggledSongIndex = toggledSongIndex,
                    isSaved = isSaved,
                )
            }.onFailure { error -> setError(error) }

            isLoading.postValue(false)
        }
    }

    private suspend fun updateToggledSong(
        songs: List<StorageDetailsSong>, toggledSongIndex: Int, isSaved: Boolean
    ) {
        val newSongs = songs.toPersistentList().mutate { persistentSongs ->
            val toggledSong = persistentSongs[toggledSongIndex]

            toggleSavedSongUseCase.toggleSavedSong(toggledSong.song)

            persistentSongs[toggledSongIndex] = toggledSong.copy(isSaved = !isSaved)
        }

        storageSongs.postValue(newSongs)
    }
}