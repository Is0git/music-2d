package com.is0.music2d.utils.composable.image.avatar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme

@Composable
fun ChunkedImagesComponent(
    modifier: Modifier = Modifier,
    gap: Dp = 2.dp,
    columnCount: Int = 3,
    images: List<String>
) {
    val imagesChunked: List<List<String>> = remember(images) {
        images.chunked(columnCount)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .width(IntrinsicSize.Max)
            .background(AppTheme.colors.onSurfaceColor),
        verticalArrangement = Arrangement.spacedBy(gap),
    ) {
        imagesChunked.forEach { imagesChunked ->
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(gap)
            ) {
                imagesChunked.forEach { image ->
                    ChunkedImageComponent(
                        modifier = Modifier.weight(1f),
                        image = image,
                    )
                }
            }
        }
    }
}

@Composable
private fun ChunkedImageComponent(
    modifier: Modifier = Modifier,
    image: String,
) {
    Box(
        modifier = modifier,
    ) {
        ImageComponent(
            modifier = Modifier.fillMaxSize(),
            imageUrl = image,
            contentScale = ContentScale.Crop,
        )
    }
}