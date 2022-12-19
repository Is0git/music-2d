package com.is0.music2d.music.playback

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.core.util.toRange
import com.is0.music2d.theme.AppTheme

private val SeekBarHeight = 6.dp
private val SeekButtonRadius = 14.dp
private val SeekButtonBorderWidth = 2.dp

@Composable
fun PlaybackControlsComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SeekBarComponent()
    }
}

@Composable
private fun SeekBarComponent(
    modifier: Modifier = Modifier,
    progress: Float = 0.6f,
) {

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart,
    ) {
        SeekBarLineInactiveComponent()
        SeekBarLineActiveComponent(
            progress = progress,
        )
        SeekButtonComponent(
            modifier = Modifier.align(
                BiasAlignment(
                    horizontalBias = lerp(
                        start = -1f,
                        stop = 1f,
                        fraction = progress,
                    ),
                    verticalBias = 0f,
                ),
            ),
        )
    }
}

@Composable
private fun SeekBarLineActiveComponent(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
) {
    SeekBarLineComponent(
        modifier = modifier.background(
            Brush.horizontalGradient(
                colors = listOf(
                    AppTheme.colors.primaryColor,
                    AppTheme.colors.secondaryColor,
                ),
            ),
        ),
        widthFraction = progress,
    )
}

@Composable
private fun SeekBarLineInactiveComponent(
    modifier: Modifier = Modifier,
) {
    SeekBarLineComponent(
        modifier = modifier.background(
            AppTheme.colors.onBackgroundColorVariant,
        ),
    )
}


@Composable
private fun SeekBarLineComponent(
    modifier: Modifier = Modifier,
    widthFraction: Float = 1f,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(widthFraction)
            .requiredHeight(SeekBarHeight)
            .clip(CircleShape)
            .then(modifier),
    )
}

@Composable
private fun SeekButtonComponent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(SeekButtonRadius)
            .graphicsLayer {
                translationX = 4f
            }
            .background(
                color = AppTheme.colors.primaryColor,
                shape = CircleShape,
            )
            .border(
                width = SeekButtonBorderWidth,
                color = AppTheme.colors.onPrimaryColor,
                shape = CircleShape,
            ),
    )
}


@Composable
@Preview
private fun PlaybackControlsComponentPreview() {
    AppTheme {
        PlaybackControlsComponent()
    }
}