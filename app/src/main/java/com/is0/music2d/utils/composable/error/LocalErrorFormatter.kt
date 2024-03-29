package com.is0.music2d.utils.composable.error

import androidx.compose.runtime.compositionLocalOf
import com.is0.music2d.utils.error.formatter.ErrorFormatter

val LocalErrorFormatter = compositionLocalOf<ErrorFormatter> {
    error("No error formatter has been provided")
}