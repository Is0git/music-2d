package com.is0.music2d.utils.composable.gradient.scrim

import androidx.annotation.FloatRange
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow

fun Modifier.verticalGradientScrim(
    color: Color,
    @FloatRange(from = 0.0, to = 1.0) startYPercentage: Float = 0f,
    @FloatRange(from = 0.0, to = 1.0) endYPercentage: Float = 1f,
    decay: Float = 1.0f,
    numStops: Int = 16
): Modifier = composed {
    val colors = remember(color, numStops) {
        if (decay != 1f) {
            val baseAlpha = color.alpha
            List(numStops) { index ->
                val gradientLerp = index * 1f / (numStops - 1)
                val opacity = gradientLerp.pow(decay)
                color.copy(alpha = baseAlpha * opacity)
            }
        } else {
            listOf(color.copy(alpha = 0f), color)
        }
    }

    val brush = remember(colors, startYPercentage, endYPercentage) {
        Brush.verticalGradient(
            colors = if (startYPercentage < endYPercentage) colors else colors.reversed(),
        )
    }

    drawBehind {
        val topLeft = Offset(0f, size.height * min(startYPercentage, endYPercentage))
        val bottomRight = Offset(size.width, size.height * max(startYPercentage, endYPercentage))

        drawRect(
            topLeft = topLeft,
            size = Rect(topLeft, bottomRight).size,
            brush = brush
        )
    }
}
