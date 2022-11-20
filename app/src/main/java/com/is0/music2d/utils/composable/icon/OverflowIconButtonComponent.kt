package com.is0.music2d.utils.composable.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.theme.AppTheme

@Composable
fun OverflowIconButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    IconButton(onClick = onClick) {
        Icon(
            modifier = modifier,
            imageVector = Icons.Filled.MoreVert,
            tint = AppTheme.colors.onSurfaceColor,
            contentDescription = "",
        )
    }
}