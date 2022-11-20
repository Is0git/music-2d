package com.is0.music2d.main.home.details.storage.use_case

import app.cash.turbine.test
import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import com.is0.music2d.music.song.storage.utils.merge.SongsMergeResult
import com.is0.music2d.music.song.utils.data.database.data.repository.DatabaseSongsRepository
import com.is0.music2d.music.song.utils.data.domain.SongMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class WatchStorageSongsDetailsUseCaseImplTest {
    lateinit var watchStorageSongsDetailsUseCaseImpl: WatchStorageSongsDetailsUseCaseImpl
    lateinit var databaseSongsRepository: DatabaseSongsRepository
    lateinit var savedSongsRepository: SavedSongsRepository
    lateinit var savedSongsMerger: SavedSongsMerger

    @Before
    fun setUp() {
        databaseSongsRepository = mock()
        savedSongsRepository = mock()
        savedSongsMerger = mock()

        watchStorageSongsDetailsUseCaseImpl = WatchStorageSongsDetailsUseCaseImpl(
            databaseSongsRepository = databaseSongsRepository,
            savedSongsRepository = savedSongsRepository,
            savedSongsMerger = savedSongsMerger,
        )
    }

    @Test
    fun givenSongsMerged_watchStorageSongsDetails_returnsSavedStorageSongs() {
        runTest {
            val songs = SongMock.generateSongs()
            val savedSongs = listOf(
                SavedSong(
                    songs.first().id,
                    SongStorageType.NONE,
                ),
            )
            given(databaseSongsRepository.watchSongs()).willReturn(flowOf(songs))
            given(savedSongsRepository.watchSavedSongs()).willReturn(flowOf(savedSongs))
            given(savedSongsMerger.mergeSavedSongs(songs, savedSongs)).willReturn(
                SongsMergeResult.Merged(
                    mapOf(songs.first() to listOf(savedSongs.first().songStorageType)),
                )
            )

            watchStorageSongsDetailsUseCaseImpl.watchStorageSongsDetails().test {
                val storageDetailsSongs = awaitItem()

                assertThat(storageDetailsSongs.first().isSaved, `is`(true))

                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}