package com.is0.music2d.utils.composable.error

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.LiveData
import com.is0.music2d.utils.composable.local.LocalSnackBarHostState
import com.is0.music2d.utils.observer.observe
import kotlinx.coroutines.launch

@Composable
fun LiveData<Throwable>.handleSnackbarError(
) {
    val errorFormatter = LocalErrorFormatter.current
    val snackbarHostState = LocalSnackBarHostState.current

    val coroutineScope = rememberCoroutineScope()

    observe { error ->
        val errorText = errorFormatter.formatError(error)
        coroutineScope.launch {
            snackbarHostState.showSnackbar(errorText)
        }
    }
}