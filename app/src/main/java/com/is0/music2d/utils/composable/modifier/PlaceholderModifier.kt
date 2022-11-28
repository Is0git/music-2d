package com.is0.music2d.utils.composable.modifier

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.placeholder.shimmer
import com.is0.music2d.theme.AppTheme

@Composable
fun Modifier.placeholder(
    visible: Boolean,
    shape: Shape = RectangleShape,
): Modifier = this.composed {
    if (visible) {
        Modifier.placeholder(
            visible = true,
            color = AppTheme.colors.placeholderColor,
            highlight = PlaceholderHighlight.shimmer(AppTheme.colors.primaryColor),
            shape = shape,
        )
    } else {
        this
    }
}