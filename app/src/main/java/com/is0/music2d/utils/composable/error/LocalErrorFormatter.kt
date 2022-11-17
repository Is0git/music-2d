package com.is0.music2d.utils.composable.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.duration.DurationFormatter
import com.is0.music2d.utils.error.formatter.ErrorFormatter
import com.is0.music2d.utils.size.DefaultFileSizeFormatter

val LocalErrorFormatter = compositionLocalOf<ErrorFormatter> {
    error("No error formatter has been provided")
}

@Composable
fun AppProviders(
    errorFormatter: ErrorFormatter,
    durationFormatter: DurationFormatter,
    sizeFormatter: DefaultFileSizeFormatter,
    content: @Composable () -> Unit = {},
) {
    CompositionLocalProvider(
        LocalErrorFormatter provides errorFormatter,
        LocalSizeFormatter provides sizeFormatter,
        LocalDurationFormatter provides durationFormatter,
    ) {
        content()
    }
}