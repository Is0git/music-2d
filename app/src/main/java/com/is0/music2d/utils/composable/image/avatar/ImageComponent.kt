package com.is0.music2d.utils.composable.image.avatar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.request.ImageRequest
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun ImageComponent(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    contentScale: ContentScale = ContentScale.Crop,
    alignment: Alignment = Alignment.Center,
    key: String? = null,
) {
    val context = LocalContext.current

    CoilImage(
        modifier = modifier,
        imageRequest = {
            ImageRequest.Builder(context)
                .data(imageUrl)
                .memoryCacheKey(key)
                .build()
        },
        imageOptions = ImageOptions(
            contentScale = contentScale,
            alignment = alignment,
        ),
    )
}