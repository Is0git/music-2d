package com.is0.music2d.utils.composable.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.is0.music2d.theme.AppTheme

@Composable
fun LabelLayoutComponent(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallComponentGap),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        text()
    }
}
