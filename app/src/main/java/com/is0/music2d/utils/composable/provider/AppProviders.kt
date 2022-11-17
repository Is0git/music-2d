package com.is0.music2d.utils.composable.provider

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.is0.music2d.utils.composable.error.LocalErrorFormatter
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.duration.DurationFormatter
import com.is0.music2d.utils.error.formatter.ErrorFormatter
import com.is0.music2d.utils.size.DefaultFileSizeFormatter

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