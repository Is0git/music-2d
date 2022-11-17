package com.is0.music2d.utils.analytics

interface EventLogger {
    fun logEvent(event: String, vararg params: Pair<String, *>)
}