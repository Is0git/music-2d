package com.is0.music2d.music.album.utils.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.is0.music2d.music.album.utils.data.database.dao.AlbumsDao
import com.is0.music2d.music.album.utils.data.database.dao.AlbumsWithSongsDao
import com.is0.music2d.music.album.utils.data.domain.AlbumMock
import com.is0.music2d.music.song.utils.data.database.data.SongsDao
import com.is0.music2d.utils.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DatabaseAlbumRepositoryTest {
    lateinit var databaseAlbumsRepository: DatabaseAlbumsRepository
    lateinit var database: AppDatabase
    lateinit var albumsWithSongsDao: AlbumsWithSongsDao
    lateinit var albumsDao: AlbumsDao
    lateinit var songsDao: SongsDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        albumsWithSongsDao = database.albumsWithSongsDao()
        albumsDao = database.albumDao()
        songsDao = database.songsDao()

        databaseAlbumsRepository = DatabaseAlbumsRepository(
            albumsWithSongsDao = albumsWithSongsDao,
            albumsDao = albumsDao,
            songsDao = songsDao,
            appDatabase = database,
            dispatcher = Dispatchers.IO,
        )
    }

    @Test
    fun addAlbums_returnsAlbums() {
        runBlocking {
            val mockedAlbums = AlbumMock.getRandomAlbums(1)

            databaseAlbumsRepository.addAlbums(mockedAlbums)

            databaseAlbumsRepository.watchAlbums(-1).test {
                awaitItem().also { albums -> assertThat(albums, `is`(mockedAlbums)) }

                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun givenSongsCount_andHasAlbums_watchAlbums_returnsAlbumsWithExactOrLessCount() {
        runBlocking {
            val mockedAlbums = AlbumMock.getRandomAlbums(10)
            databaseAlbumsRepository.addAlbums(mockedAlbums)

            val songCount = 6

            databaseAlbumsRepository.watchAlbums(songCount).test {
                awaitItem().also { albums -> assertThat(albums.all { it.songs.size <= songCount }, `is`(true)) }

                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun givenHasAlbums_watchAlbums_returnsAllAlbums() {
        runBlocking {
            val mockedAlbums = AlbumMock.getRandomAlbums(10)
            databaseAlbumsRepository.addAlbums(mockedAlbums)

            databaseAlbumsRepository.watchAlbums(-1).test {
                awaitItem().also { albums -> assertThat(mockedAlbums, `is`(albums)) }

                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @Test
    fun givenHasAlbums_exists_returnsTrue() {
        runBlocking {
            val mockedAlbums = AlbumMock.getRandomAlbums(1)
            databaseAlbumsRepository.addAlbums(mockedAlbums)

            val exists = databaseAlbumsRepository.exists()

            assertThat(exists, `is`(true))
        }
    }

    @Test
    fun givenHasNoAlbums_exists_returnsFlase() {
        runBlocking {
            val exists = databaseAlbumsRepository.exists()

            assertThat(exists, `is`(false))
        }
    }

    @After
    fun closeDatabase() {
        database.close()
    }
}