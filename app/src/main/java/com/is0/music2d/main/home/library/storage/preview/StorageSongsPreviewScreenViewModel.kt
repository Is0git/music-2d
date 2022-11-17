package com.is0.music2d.main.home.library.storage.preview

import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StorageSongsPreviewScreenViewModel @Inject constructor(

) : BaseViewModel() {
    val storageSongsPreview = createSingleLiveData<List<StorageSongsPreview>>()
}