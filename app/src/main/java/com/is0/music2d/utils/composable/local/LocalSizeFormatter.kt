package com.is0.music2d.utils.composable.local

import androidx.compose.runtime.compositionLocalOf
import com.is0.music2d.utils.size.FileSizeFormatter

val LocalSizeFormatter = compositionLocalOf<FileSizeFormatter> { error("Size formatter has not been provided") }