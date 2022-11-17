package com.is0.music2d.utils.di

import com.is0.music2d.utils.di.qualifier.Default
import com.is0.music2d.utils.di.qualifier.IO
import com.is0.music2d.utils.di.qualifier.Main
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutineDispatchersModule {
    @Provides
    @Singleton
    @Main
    fun provideMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @Singleton
    @IO
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Singleton
    @Default
    fun provideDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}