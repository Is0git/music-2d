package com.is0.music2d.utils.composable.padding

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun VerticalSpacerComponent(
    modifier: Modifier = Modifier,
    height: Dp,
) {
    Spacer(modifier = modifier.requiredHeight(height))
}

@Composable
fun HorizontalSpacerComponent(
    modifier: Modifier = Modifier,
    width: Dp,
) {
    Spacer(modifier = modifier.requiredWidth(width))
}