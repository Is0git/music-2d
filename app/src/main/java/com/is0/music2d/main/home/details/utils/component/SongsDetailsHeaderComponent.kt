package com.is0.music2d.main.home.details.utils.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.image.avatar.ChunkedImagesComponent
import com.is0.music2d.utils.data.mock.ImageMock

@Composable
fun SongsDetailsHeaderComponent(
    modifier: Modifier = Modifier,
    images: List<String>,
    title: String = "",
) {
    Box(modifier.aspectRatio(4 / 3f)) {
        ChunkedImagesComponent(
            images = images,
        )
        HeaderOverlayComponent()
        AnimatedDetailsTitleComponent(
            title = title,
        )
    }
}

@Composable
private fun HeaderOverlayComponent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(bottom = 2.dp)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        AppTheme.colors.onSurfaceColorVariant.copy(0.9f),
                        AppTheme.colors.secondaryColor.copy(0.7f),
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
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .graphicsLayer {
                val scale = 1.5f
                scaleX = scale
                scaleY = scale

                clip = true
            }
            .background(
                Brush.radialGradient(
                    colorStops = arrayOf(
                        0.1f to Color.Transparent,
                        1f to AppTheme.colors.primaryColor.copy(0.8f),
                    ),
                ),
            )
    )
}

@ExperimentalAnimationApi
@Composable
@Preview
private fun AlbumHeaderComponentPreview() {
    AppTheme {
        SongsDetailsHeaderComponent(
            images = (0..4).map { ImageMock.image },
            title = "Best album ever"
        )
    }
}