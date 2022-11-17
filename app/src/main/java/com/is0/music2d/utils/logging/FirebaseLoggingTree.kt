package com.is0.music2d.utils.logging

import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import timber.log.Timber

class FirebaseLoggingTree(
    private val firebaseCrashlytics: FirebaseCrashlytics,
) : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority > Log.INFO) {
            val logMessage = """
                |Message: $message
                |Tag: $tag
            """.trimMargin()
            firebaseCrashlytics.log(logMessage)
            t?.let { throwable ->
                firebaseCrashlytics.recordException(throwable)
            }
        }
    }
}