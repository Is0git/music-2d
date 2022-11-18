package com.is0.music2d.music.album.utils.di

import com.is0.music2d.music.album.utils.data.database.dao.AlbumsDao
import com.is0.music2d.music.album.utils.data.database.dao.AlbumsWithSongsDao
import com.is0.music2d.utils.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AlbumModule {
    @Provides
    fun provideAlbumsDao(appDatabase: AppDatabase): AlbumsDao = appDatabase.albumDao()

    @Provides
    fun provideAlbumsWithSongsDao(appDatabase: AppDatabase): AlbumsWithSongsDao = appDatabase.albumsWithSongsDao()
}