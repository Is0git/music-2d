package com.is0.music2d.main.home.library.storage_preview

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.library.storage_preview.use_case.WatchSongsPreviewsUseCase
import com.is0.music2d.main.home.library.storage_preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class StorageSongsPreviewScreenViewModel @Inject constructor(
    private val watchStorageSongsPreviewsUseCase: WatchSongsPreviewsUseCase,
) : BaseViewModel() {
    val storageSongsPreview = createMutableLiveData<List<StorageSongsPreview>>(emptyList())

    init {
        watchStorageSongs()
    }

    private fun watchStorageSongs() {
        watchStorageSongsPreviewsUseCase.watchSongsPreviews()
            .withStateHandler()
            .onEach { storageSongsPreviews ->
                storageSongsPreview.setValue(storageSongsPreviews)
            }.launchIn(viewModelScope)
    }
}