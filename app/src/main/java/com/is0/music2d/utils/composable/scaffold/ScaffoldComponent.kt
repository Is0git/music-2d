package com.is0.music2d.utils.composable.scaffold

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.theme.color.AppColor
import com.is0.music2d.utils.composable.button.BackButtonComponent
import com.is0.music2d.utils.composable.gradient.scrim.ScrimComponent
import com.is0.music2d.utils.composable.gradient.scrim.verticalGradientScrim
import com.is0.music2d.utils.composable.progress.ProgressComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    isLoading: Boolean = false,
    onBackClick: (() -> Unit)? = null,
    snackbarHostState: SnackbarHostState = rememberSnackBarHostState(),
    navigationIcon: @Composable () -> Unit = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit = {},
    isAppBarCollapsable: Boolean = false,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.then(
            if (isAppBarCollapsable) {
                Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
            } else {
                Modifier
            },
        ),
        containerColor = AppTheme.colors.imageScrimColor,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { paddingValues ->
            Box(modifier = Modifier.fillMaxSize()) {
                ScrimComponent(
                    color = AppTheme.colors.primaryColor,
                    startYPercentage = 1f,
                    endYPercentage = 0f,
                )
                content(paddingValues)
                if (isLoading) {
                    ProgressComponent(modifier = Modifier.align(Alignment.Center))
                }
            }
        },
        topBar = {
            val icon: @Composable () -> Unit = {
                if (onBackClick != null) {
                    BackButtonComponent(onClick = onBackClick)
                } else {
                    navigationIcon()
                }
            }
            if (isAppBarCollapsable) {
                LargeTopAppBar(
                    navigationIcon = icon,
                    title = { Text(text = title) },
                    scrollBehavior = scrollBehavior,
                )
            } else {
                SmallTopAppBar(
                    modifier = Modifier.verticalGradientScrim(Color.Red),
                    navigationIcon = icon,
                    title = { Text(text = title) },
                )
            }
        },
    )
}