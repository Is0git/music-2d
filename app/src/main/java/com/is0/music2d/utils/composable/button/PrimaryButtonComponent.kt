package com.is0.music2d.utils.composable.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.theme.AppTheme

@Composable
fun PrimaryButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "",
) {
    ButtonComponent(
        modifier = modifier,
        onClick = onClick,
        text = text,
        containerColor = AppTheme.colors.primaryColor,
        contentColor = AppTheme.colors.onPrimaryColor,
        shape = AppTheme.shapes.primaryButtonShape,
    )
}