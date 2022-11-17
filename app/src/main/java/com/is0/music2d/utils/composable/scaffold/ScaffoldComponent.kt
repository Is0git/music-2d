package com.is0.music2d.utils.composable.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.is0.music2d.utils.composable.button.BackButtonComponent
import com.is0.music2d.utils.composable.progress.ProgressComponent
import com.is0.music2d.utils.composable.text.TitleLargeTextComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    isLoading: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    snackbarHostState: SnackbarHostState = rememberSnackBarHostState(),
    content: @Composable (paddingValues: PaddingValues) -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                content(paddingValues)
                if (isLoading) {
                    ProgressComponent(modifier = Modifier.align(Alignment.Center))
                }
            }
        },
        topBar = {
            SmallTopAppBar(
                navigationIcon = {
                    onBackClick?.let { onCLick ->
                        BackButtonComponent(onClick = onCLick)
                    }
                },
                title = { TitleLargeTextComponent(text = title) },
            )
        },
    )
}