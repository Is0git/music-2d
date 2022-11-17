package com.is0.music2d.main.home

import com.is0.music2d.main.home.utils.data.SongsContentType
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {
    val songContentTypes = listOf(SongsContentType.ALBUMS, SongsContentType.STORAGE)

    val selectedSongContentType = createMutableLiveData(SongsContentType.ALBUMS)
    val username = createMutableLiveData(FAKE_USERNAME)

    fun selectContentType(contentType: SongsContentType) = selectedSongContentType.setValue(contentType)

    companion object {
        const val FAKE_USERNAME = "Tim"
    }
}