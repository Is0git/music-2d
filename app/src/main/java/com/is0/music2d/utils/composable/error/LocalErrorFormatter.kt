package com.is0.music2d.utils.composable.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.is0.music2d.utils.error.formatter.ErrorFormatter

val LocalErrorFormatter = compositionLocalOf<ErrorFormatter> {
    error("No error formatter has been provided")
}

@Composable
fun ErrorFormatterProvider(
    errorFormatter: ErrorFormatter,
    content: @Composable () -> Unit = {},
) {
    CompositionLocalProvider(LocalErrorFormatter provides errorFormatter) {
        content()
    }
}