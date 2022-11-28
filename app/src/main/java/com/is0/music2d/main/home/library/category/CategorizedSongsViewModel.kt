package com.is0.music2d.main.home.library.category

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.library.category.use_case.WatchSongsCategoriesUseCase
import com.is0.music2d.main.home.library.category.utils.data.domain.SongsCategory
import com.is0.music2d.utils.const.LOADING_DELAY_MILLIS
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class CategorizedSongsViewModel @Inject constructor(
    private val watchSongsCategoriesUseCase: WatchSongsCategoriesUseCase,
) : BaseViewModel() {
    val songsCategories = createMutableLiveData<List<SongsCategory>>(emptyList())

    init {
        watchSongCategories()
    }

    private fun watchSongCategories() {
        watchSongsCategoriesUseCase.watchSongsCategories(CATEGORY_SONGS_COUNT)
            .onStart { delay(LOADING_DELAY_MILLIS) }
            .withStateHandler()
            .onEach { newSongCategories -> songsCategories.setValue(newSongCategories) }
            .launchIn(viewModelScope)
    }

    companion object {
        const val CATEGORY_SONGS_COUNT = 5
    }
}