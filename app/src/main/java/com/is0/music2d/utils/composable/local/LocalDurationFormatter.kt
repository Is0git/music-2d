package com.is0.music2d.utils.composable.local

import androidx.compose.runtime.compositionLocalOf
import com.is0.music2d.utils.duration.DurationFormatter

val LocalDurationFormatter = compositionLocalOf<DurationFormatter> { error("No duration formatter has been provided") }