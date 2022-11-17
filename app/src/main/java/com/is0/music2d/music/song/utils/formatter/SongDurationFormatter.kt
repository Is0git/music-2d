package com.is0.music2d.music.song.utils.formatter

import android.text.format.DateUtils
import com.is0.music2d.utils.duration.DurationFormatter
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongDurationFormatter @Inject constructor() : DurationFormatter {
    override fun formatDuration(durationMillis: Long): String = runCatching {
        when {
            durationMillis >= DateUtils.HOUR_IN_MILLIS -> {
                val hours: Long = durationMillis / DateUtils.HOUR_IN_MILLIS
                val minutes: Long = (durationMillis - hours * DateUtils.HOUR_IN_MILLIS) / DateUtils.MINUTE_IN_MILLIS
                String.format(HOURS_MINUTES_FORMAT, hours, minutes)
            }
            durationMillis >= DateUtils.MINUTE_IN_MILLIS -> {
                val minutes: Long = durationMillis / DateUtils.MINUTE_IN_MILLIS
                val seconds: Long =
                    (durationMillis - minutes * DateUtils.MINUTE_IN_MILLIS) / DateUtils.SECOND_IN_MILLIS
                String.format(MINUTES_SECONDS_FORMAT, minutes, seconds)
            }
            else -> {
                val seconds = (durationMillis / DateUtils.SECOND_IN_MILLIS).toInt()
                String.format(SECONDS_FORMAT, seconds)
            }
        }
    }
        .onFailure(Timber::e)
        .getOrNull()
        .orEmpty()

    companion object {
        private const val HOURS_MINUTES_FORMAT = "%sh %smin"
        private const val MINUTES_SECONDS_FORMAT = "%smin %ss"
        private const val SECONDS_FORMAT = "%ss"
    }
}