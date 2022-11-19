package com.is0.music2d.music.song.storage.filesystem.utils.di

import com.is0.music2d.music.song.storage.filesystem.utils.data.database.dao.FilesystemSongsDao
import com.is0.music2d.utils.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object FilesystemSongsModule {
    @Provides
    @ViewModelScoped
    fun provideFilesystemSongsDao(
        appDatabase: AppDatabase,
    ): FilesystemSongsDao = appDatabase.filesystemSongsDao()
}