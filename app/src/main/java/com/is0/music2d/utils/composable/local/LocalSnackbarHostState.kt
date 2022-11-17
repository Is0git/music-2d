package com.is0.music2d.utils.composable.local

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.compositionLocalOf

val LocalSnackBarHostState = compositionLocalOf<SnackbarHostState> { error("SnackBarHostState has not been provided") }