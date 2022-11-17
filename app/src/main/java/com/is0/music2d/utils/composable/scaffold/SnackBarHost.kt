package com.is0.music2d.utils.composable.scaffold

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberSnackBarHostState() = remember {
    SnackbarHostState()
}