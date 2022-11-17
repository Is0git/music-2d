package com.is0.music2d.main.home.library.storage.preview

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.library.storage.preview.use_case.WatchStorageSongsPreviewsUseCase
import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageSongsPreviewScreenViewModel @Inject constructor(
    private val watchStorageSongsPreviewsUseCase: WatchStorageSongsPreviewsUseCase,
) : BaseViewModel() {
    val storageSongsPreview = createSingleLiveData<List<StorageSongsPreview>>()

    init {
        viewModelScope.launch {
            watchStorageSongs()
        }
    }

    private suspend fun watchStorageSongs() {
        watchStorageSongsPreviewsUseCase.watchStorageSongsPreviews()
            .catch { error -> setError(error) }
            .collect { storageSongsPreviews ->
                storageSongsPreview.setValue(storageSongsPreviews)
            }
    }
}