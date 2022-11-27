package com.is0.music2d.music.song.storage

import androidx.lifecycle.viewModelScope
import com.is0.music2d.music.song.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoredSongsToggleViewModel @Inject constructor(
    private val toggleSavedSongUseCase: ToggleSavedSongUseCase,
) : BaseViewModel() {
    val availableSongStorageTypes: List<SongStorageType> = allSongStorageTypes()

    fun toggleSavedSong(
        songId: String,
        songStorageType: SongStorageType,
    ) {
        viewModelScope.launch {
            runCatching {
                isLoading.postValue(true)

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