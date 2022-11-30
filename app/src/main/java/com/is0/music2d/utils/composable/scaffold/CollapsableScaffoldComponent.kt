package com.is0.music2d.utils.composable.scaffold

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.button.BackButtonComponent
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

private val TopBarHeight: Dp = 64.dp
private val TopBarHorizontalPadding: Dp = 4.dp

@Composable
fun CollapsableScaffoldComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    toolBarBackground: @Composable () -> Unit = {},
    onNavigateUp: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    val collapsingToolbarState = rememberCollapsingToolbarScaffoldState()

    val alpha by animateFloatAsState(
        targetValue = if (collapsingToolbarState.toolbarState.progress < 0.2f) 0.3f else 1f,
        animationSpec = tween(1000),
    )

    CollapsingToolbarScaffold(
        modifier = modifier.fillMaxSize(),
        toolbar = {
            Box(
                modifier = Modifier
                    .background(color = AppTheme.colors.backgroundColor)
                    .alpha(alpha),
            ) {
                toolBarBackground()
            }
            ToolbarComponent(
                modifier = modifier.pin(),
                title = title,
                onBackClick = onNavigateUp,
            )
        },
        scrollStrategy = ScrollStrategy.ExitUntilCollapsed,
        state = collapsingToolbarState,
    ) {
        content()
    }
}

@Composable
private fun ToolbarComponent(
    modifier: Modifier = Modifier,
    title: String,
    onBackClick: () -> Unit
) {
    CompositionLocalProvider(LocalContentColor provides AppTheme.colors.onSurfaceColor) {
        TopBarLayoutComponent(
            modifier = modifier,
            title = {
                TopBarTitleComponent(
                    title = title,
                )
            },
            navigationIcon = {
                BackButtonComponent(
                    onClick = onBackClick,
                )
            }
        )
    }
}

@Composable
private fun TopBarLayoutComponent(
    modifier: Modifier = Modifier,
    title: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    alpha: Float = 0f,
) {
    Row(
        modifier = modifier
            .background(AppTheme.colors.backgroundColor.copy(alpha))
            .fillMaxWidth()
            .padding(horizontal = TopBarHorizontalPadding)
            .requiredHeight(TopBarHeight),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        navigationIcon()
        title()
    }
}

@Composable
private fun TopBarTitleComponent(
    modifier: Modifier = Modifier,
    title: String = "",
) {
    Text(
        modifier = modifier,
        text = title,
        style = MaterialTheme.typography.titleLarge,
        maxLines = 1,
    )
}