@file:OptIn(ExperimentalAnimationApi::class)

package com.is0.music2d.main.home.details.utils.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.layout.LabelLayoutComponent
import com.is0.music2d.utils.composable.modifier.transparentBackground
import com.is0.music2d.utils.composable.text.LabelSmallTextComponent

@Composable
fun SongHeaderInfoElementComponent(
    modifier: Modifier = Modifier,
    isVisible: Boolean = true,
    text: String,
    color: Color = AppTheme.colors.onSurfaceColor,
    icon: @Composable () -> Unit = {},
) {

    CompositionLocalProvider(LocalContentColor provides color) {
        AnimatedVisibility(
            modifier = modifier,
            enter = fadeIn() + scaleIn(),
            exit = fadeOut() + scaleOut(),
            visible = isVisible,
        ) {
            LabelLayoutComponent(
                modifier = Modifier.transparentBackground(),
                text = {
                    LabelSmallTextComponent(
                        modifier = modifier,
                        text = text,
                    )
                },
                icon = icon,
            )
        }
    }
}