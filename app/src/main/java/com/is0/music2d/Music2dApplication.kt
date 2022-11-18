@file:OptIn(DelicateCoroutinesApi::class)

package com.is0.music2d

import android.app.Application
import com.is0.music2d.music.use_case.PopulateMusicUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class Music2dApplication : Application() {
    @Inject
    lateinit var populateMusicUseCase: PopulateMusicUseCase

    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch {
            runCatching { populateMusicUseCase.populateMusic() }
                .onFailure(Timber::e)
        }
    }
}