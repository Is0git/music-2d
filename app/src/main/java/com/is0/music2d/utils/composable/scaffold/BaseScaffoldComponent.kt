package com.is0.music2d.utils.composable.scaffold

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.is0.music2d.utils.composable.error.LocalErrorFormatter
import com.is0.music2d.utils.composable.local.LocalSnackBarHostState
import com.is0.music2d.utils.observer.observe
import com.is0.music2d.utils.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

@Composable
fun BaseScaffoldComponent(
    modifier: Modifier = Modifier,
    baseViewModel: BaseViewModel,
    title: String = "",
    snackbarHostState: SnackbarHostState = rememberSnackBarHostState(),
    onNavigateUp: (() -> Unit)? = null,
    navigationIcon: @Composable () -> Unit = {},
    bottomBar: (@Composable () -> Unit)? = null,
    isAppBarCollapsable: Boolean = false,
    isLoading: Boolean = false,
    content: @Composable (paddingValues: PaddingValues) -> Unit = {},
) {
    val isBackButtonEnabled = onNavigateUp != null
    val isLoadingInternal by baseViewModel.isLoading.observeAsState(false)

    val errorFormatter = LocalErrorFormatter.current

    val coroutineScope = rememberCoroutineScope()

    baseViewModel.error.observe { error ->
        val errorText = errorFormatter.formatError(error)
        coroutineScope.launch {
            snackbarHostState.showSnackbar(errorText)
        }
    }

    baseViewModel.navigateUp.observe {
        onNavigateUp?.invoke()
    }
    CompositionLocalProvider(LocalSnackBarHostState provides snackbarHostState) {
        ScaffoldComponent(
            modifier = modifier,
            snackbarHostState = snackbarHostState,
            title = title,
            isAppBarCollapsable = isAppBarCollapsable,
            bottomBar = bottomBar,
            onBackClick = if (isBackButtonEnabled) baseViewModel::onBackSelected else null,
            isLoading = isLoadingInternal || isLoading,
            navigationIcon = navigationIcon,
            content = content
        )
    }
}