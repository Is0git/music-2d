package com.is0.music2d.main.home.details.song

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.details.song.use_case.WatchSongDetailsUseCase
import com.is0.music2d.main.home.details.song.utils.data.domain.SongDetails
import com.is0.music2d.main.home.details.song.utils.data.domain.toSongDetails
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SongDetailsViewModel @Inject constructor(
    private val watchSongDetailsUseCase: WatchSongDetailsUseCase,
) : BaseViewModel() {
    val songDetails = createMutableLiveData<SongDetails?>(null)

    fun watchSongDetails(song: Song) {
        viewModelScope.launch {
            if (songDetails.value == null) {
                launch {
                    setInitialSongDetails(song)
                }
                launch {
                    watchSongDetailsUseCase.watchSongDetails(song.id)
                        .onEach { details -> songDetails.postValue(details) }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    private fun setInitialSongDetails(song: Song) {
        flow { emit(song) }
            .map { it.toSongDetails() }
            .handleErrors()
            .onEach { details ->
                songDetails.postValue(details)
            }
    }
}