package com.is0.music2d.main.home.details.storage

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.is0.music2d.main.home.details.storage.use_case.ToggleSavedSongUseCase
import com.is0.music2d.main.home.details.storage.use_case.WatchStorageSongsDetailsUseCase
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.music.song.utils.data.domain.SongMock
import com.is0.music2d.test.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class StorageDetailsViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var storageDetailsViewModel: StorageDetailsViewModel
    private lateinit var watchStorageSongsDetailsUseCase: WatchStorageSongsDetailsUseCase
    private lateinit var toggleSavedSongUseCase: ToggleSavedSongUseCase

    @Before
    fun setUp() {
        watchStorageSongsDetailsUseCase = mock()
        toggleSavedSongUseCase = mock()

        storageDetailsViewModel = StorageDetailsViewModel(
            watchStorageSongsDetailsUseCase = watchStorageSongsDetailsUseCase,
            toggleSavedSongUseCase = toggleSavedSongUseCase,
        )
    }

    @Test
    fun givenThrowsError_watchStorageSongs_showError() {
        runTest {
            val error = Exception("Error")
            given(watchStorageSongsDetailsUseCase.watchStorageSongsDetails()).willReturn(flow { throw error })

            storageDetailsViewModel.watchStorageSongs()

            assertThat(storageDetailsViewModel.error.value, `is`(error))
        }
    }

    @Test
    fun watchStorageSongs_returnsStorageSongs() {
        runTest {
            val songs = listOf(StorageDetailsSong(song = SongMock.generateRandomSong(), isSaved = false))
            given(watchStorageSongsDetailsUseCase.watchStorageSongsDetails()).willReturn(flowOf(songs))

            storageDetailsViewModel.watchStorageSongs()

            assertThat(storageDetailsViewModel.storageDetails.value?.songs, `is`(songs))
        }
    }
}