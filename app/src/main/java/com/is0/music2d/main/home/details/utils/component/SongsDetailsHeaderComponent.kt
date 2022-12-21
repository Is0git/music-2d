package com.is0.music2d.main.home.details.utils.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.is0.music2d.R
import com.is0.music2d.music.song.utils.component.icon.SongDurationIconComponent
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.SongIconComponent
import com.is0.music2d.utils.composable.image.avatar.ChunkedImagesComponent
import com.is0.music2d.utils.data.mock.ImageMock

private const val HeaderAspectRatio = 4 / 3f
private const val OverlayAnimationDuration = 1000
private const val OverlayTintAnimationDuration = 2500

@Composable
fun SongsDetailsHeaderComponent(
    modifier: Modifier = Modifier,
    images: List<String>,
    title: String = "",
    durationText: String = "",
    songCount: Int = 0,
) {
    Box(modifier.aspectRatio(HeaderAspectRatio)) {
        ChunkedImagesComponent(
            images = images,
        )
        HeaderOverlayComponent()
        AnimatedDetailsTitleComponent(
            title = title,
        )
        SongHeaderInfoComponent(
            modifier = Modifier.align(Alignment.BottomStart),
            durationText = durationText,
            songCount = songCount,
        )
    }
}

@Composable
private fun HeaderOverlayComponent(
    modifier: Modifier = Modifier,
) {
    val overlayTransition = rememberInfiniteTransition()

    val startColorValue = AppTheme.colors.onSurfaceVariantColor.copy(0.9f)
    val endColorValue = AppTheme.colors.secondaryColor.copy(0.7f)

    val startColor = overlayTransition.animateColor(
        initialColor = startColorValue,
        targetColor = endColorValue,
        durationMillis = OverlayAnimationDuration,
    )

    val endColor = overlayTransition.animateColor(
        initialColor = endColorValue,
        targetColor = startColorValue,
        durationMillis = OverlayAnimationDuration,
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 2.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        startColor,
                        endColor,
                    ),
                    startY = Float.POSITIVE_INFINITY,
                    endY = 0f,
                )
            ),
    ) {
        HeaderOverlayTintComponent()
    }
}

@Composable
private fun HeaderOverlayTintComponent(
    modifier: Modifier = Modifier,
    overlayTransition: InfiniteTransition = rememberInfiniteTransition()
) {
    val radialGradientColor = overlayTransition.animateColor(
        initialColor = AppTheme.colors.primaryColor.copy(0.8f),
        targetColor = AppTheme.colors.secondaryColor.copy(0.8f),
        durationMillis = OverlayTintAnimationDuration,
    )

    val scale by overlayTransition.animateFloat(
        initialValue = 1.5f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = OverlayTintAnimationDuration),
            repeatMode = RepeatMode.Reverse,
        ),
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .clipToBounds()
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .background(
                Brush.radialGradient(
                    colorStops = arrayOf(
                        0.1f to Color.Transparent,
                        1f to radialGradientColor,
                    ),
                ),
            )
    )
}

@Composable
private fun InfiniteTransition.animateColor(
    initialColor: Color,
    targetColor: Color,
    durationMillis: Int,
): Color {
    val infiniteAnimationSpec: InfiniteRepeatableSpec<Color> = remember {
        infiniteRepeatable(
            animation = tween(durationMillis = durationMillis),
            repeatMode = RepeatMode.Reverse,
        )
    }

    val color by this.animateColor(
        initialValue = initialColor,
        targetValue = targetColor,
        animationSpec = infiniteAnimationSpec,
    )

    return color
}

@Composable
private fun SongHeaderInfoComponent(
    modifier: Modifier = Modifier,
    durationText: String,
    songCount: Int,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SongHeaderInfoElementComponent(
            isVisible = durationText.isNotEmpty(),
            text = durationText,
            icon = { SongDurationIconComponent() },
        )
        SongHeaderInfoElementComponent(
            isVisible = songCount > 0,
            text = stringResource(
                R.string.songs_details_header_songs_count_text,
                songCount,
            ),
            icon = { SongIconComponent() },
        )
    }
}

@ExperimentalAnimationApi
@Composable
@Preview
private fun AlbumHeaderComponentPreview() {
    AppTheme {
        SongsDetailsHeaderComponent(
            images = (0..4).map { ImageMock.image },
            title = "Best album ever",
            songCount = 10,
            durationText = "24h 23min",
        )
    }
}