package com.is0.music2d.utils.composable.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.theme.AppTheme

@Composable
fun CheckmarkIconComponent(modifier: Modifier = Modifier) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.Check,
        contentDescription = "",
        tint = AppTheme.colors.positiveColor,
    )
}