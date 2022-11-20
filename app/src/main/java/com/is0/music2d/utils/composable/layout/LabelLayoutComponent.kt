package com.is0.music2d.utils.composable.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.is0.music2d.theme.AppTheme

@Composable
fun LabelLayoutComponent(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
    gap: Dp = AppTheme.dimensions.smallComponentGap,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(gap),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        text()
    }
}
