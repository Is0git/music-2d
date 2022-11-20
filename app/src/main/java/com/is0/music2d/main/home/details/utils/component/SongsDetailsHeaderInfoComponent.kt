package com.is0.music2d.main.home.details.utils.component

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
    text: String,
    color: Color = AppTheme.colors.onSurfaceColor,
    icon: @Composable () -> Unit = {},
) {
    CompositionLocalProvider(LocalContentColor provides color) {
        LabelLayoutComponent(
            modifier = modifier.transparentBackground(),
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