package com.is0.music2d.main.home.library.category

import androidx.lifecycle.viewModelScope
import com.is0.music2d.main.home.library.category.use_case.WatchSongsCategoriesUseCase
import com.is0.music2d.main.home.library.category.utils.data.presentation.SongsCategory
import com.is0.music2d.utils.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategorizedSongsViewModel @Inject constructor(
    private val watchSongsCategoriesUseCase: WatchSongsCategoriesUseCase,
) : BaseViewModel() {
    val songsCategories = createMutableLiveData<List<SongsCategory>>(emptyList())

    init {
        viewModelScope.launch {
            watchSongCategories()
        }
    }

    private suspend fun watchSongCategories() {
        watchSongsCategoriesUseCase.watchSongCategories(CATEGORY_COUNT)
            .catch { error -> setError(error) }
            .onStart { isLoading.postValue(true) }
            .collect { newSongCategories ->
                isLoading.postValue(false)
                songsCategories.setValue(newSongCategories)
            }
    }

    companion object {
        const val CATEGORY_COUNT = 5
    }
}