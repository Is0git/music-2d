package com.is0.music2d.utils.composable.gradient.scrim

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ScrimComponent(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    @FloatRange(from = 0.0, to = 1.0) startYPercentage: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) endYPercentage: Float = 1f,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .verticalGradientScrim(
                color,
                startYPercentage = startYPercentage,
                endYPercentage = endYPercentage
            ),
    )
}