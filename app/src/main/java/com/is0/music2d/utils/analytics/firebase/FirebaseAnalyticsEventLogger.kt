package com.is0.music2d.utils.analytics.firebase

import androidx.core.os.bundleOf
import com.google.firebase.analytics.FirebaseAnalytics
import com.is0.music2d.utils.analytics.EventLogger
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseAnalyticsEventLogger @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
) : EventLogger {
    override fun logEvent(event: String, vararg params: Pair<String, *>) {
        firebaseAnalytics.logEvent(event, bundleOf(*params))
    }
}