package com.is0.music2d.music.song.storage.database.di

import com.is0.music2d.utils.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SongsModule {
    @Provides
    fun provideFilesystemSongsDao(appDatabase: AppDatabase) = appDatabase.songsDao()
}