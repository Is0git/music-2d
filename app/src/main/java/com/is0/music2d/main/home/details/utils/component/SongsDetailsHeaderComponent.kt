package com.is0.music2d.main.home.details.utils.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.image.avatar.ImageComponent
import com.is0.music2d.utils.composable.text.HeadlineLargeTextComponent
import com.is0.music2d.utils.data.mock.ImageMock

@Composable
fun SongsDetailsHeaderComponent(
    modifier: Modifier = Modifier,
    images: List<String>,
    columnCount: Int = 3,
    title: String = "",
    gap: Dp = 2.dp,
) {
    val imagesChunked: List<List<String>> = images.chunked(columnCount)

    Box(Modifier.aspectRatio(16f / 12)) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .width(IntrinsicSize.Max)
                .background(AppTheme.colors.onSurfaceColor),
            verticalArrangement = Arrangement.spacedBy(gap),
        ) {
            imagesChunked.forEach { images ->
                Row(
                    modifier = Modifier.weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(gap)
                ) {
                    images.forEach {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            ImageComponent(
                                modifier = Modifier.fillMaxSize(),
                                imageUrl = it,
                                contentScale = ContentScale.Crop,
                            )
                        }
                    }
                }
            }
        }
        AlbumTitleComponent(
            modifier = Modifier.align(Alignment.BottomStart),
            title = title,
        )
        HeaderOverlayComponent()
    }
}

@Composable
private fun AlbumTitleComponent(
    modifier: Modifier = Modifier,
    title: String,
) {
    HeadlineLargeTextComponent(
        modifier = modifier
            .padding(AppTheme.dimensions.bodyMargin),
        text = title.uppercase(),
        color = AppTheme.colors.onSurfaceColor,
    )
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

@Composable
@Preview
fun AlbumHeaderComponentPreview() {
    AppTheme {
        SongsDetailsHeaderComponent(
            images = (0..4).map { ImageMock.image },
            title = "Best album ever"
        )
    }
}