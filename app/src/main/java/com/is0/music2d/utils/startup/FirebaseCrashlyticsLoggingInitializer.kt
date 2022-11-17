package com.is0.music2d.utils.startup

import android.content.Context
import androidx.startup.Initializer
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.is0.music2d.utils.logging.FirebaseLoggingTree
import timber.log.Timber

class FirebaseCrashlyticsLoggingInitializer : Initializer<Unit> {
    override fun create(context: Context) {
        val firebaseTree = FirebaseLoggingTree(FirebaseCrashlytics.getInstance())
        Timber.plant(firebaseTree)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}