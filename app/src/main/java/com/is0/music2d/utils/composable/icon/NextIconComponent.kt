package com.is0.music2d.utils.composable.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun NextIconComponent(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.ChevronRight,
        contentDescription = "",
        tint = color,
    )
}