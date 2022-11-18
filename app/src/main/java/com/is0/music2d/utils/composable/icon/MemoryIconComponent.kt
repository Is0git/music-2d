package com.is0.music2d.utils.composable.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.theme.AppTheme

@Composable
fun MemoryIconComponent(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.Memory,
        tint = AppTheme.colors.onSurfaceColor,
        contentDescription = "",
    )
}