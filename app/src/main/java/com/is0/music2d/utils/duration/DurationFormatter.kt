package com.is0.music2d.utils.duration

interface DurationFormatter {
    fun formatDuration(durationMillis: Long, showZero: Boolean = true): String
}