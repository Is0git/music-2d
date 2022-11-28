package com.is0.music2d.music.song.storage

import androidx.lifecycle.viewModelScope
import com.is0.music2d.music.song.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.utils.const.ACTION_LOADING_DELAY_MILLIS
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoredSongsToggleViewModel @Inject constructor(
    private val toggleSavedSongUseCase: ToggleSavedSongUseCase,
) : BaseViewModel() {
    val availableSongStorageTypes: List<SongStorageType> = allSongStorageTypes()

    private var toggleSavedSongJob: Job? = Job()

    fun toggleSavedSong(
        songId: String,
        songStorageType: SongStorageType,
    ) {
        toggleSavedSongJob?.cancel()

        toggleSavedSongJob = viewModelScope.launch {
            runCatching {
                isLoading.postValue(true)

                delay(ACTION_LOADING_DELAY_MILLIS)

                toggleSavedSongUseCase.toggleSavedSong(
                    songId = songId,
                    songStorageType = songStorageType,
                )
            }
                .onFailure { error -> setError(error) }

            isLoading.postValue(false)
        }
    }
}