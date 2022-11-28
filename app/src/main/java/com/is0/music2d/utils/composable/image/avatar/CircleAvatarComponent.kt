package com.is0.music2d.utils.composable.image.avatar

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.modifier.placeholder
import com.is0.music2d.utils.data.mock.ImageMock

@Composable
fun CircleAvatarComponent(
    modifier: Modifier = Modifier,
    imageUrl: String = "",
    size: Dp = 64.dp,
    borderSize: Dp = Dp.Unspecified,
    borderColor: Color = Color.Unspecified,
    key: String? = null,
    isLoading: Boolean = false,
) {
    ImageComponent(
        modifier = modifier
            .size(size)
            .placeholder(
                visible = isLoading,
                shape = CircleShape,
            )
            .clip(CircleShape)
            .border(borderSize, borderColor, CircleShape),
        imageUrl = imageUrl,
        key = key,
    )
}

@Composable
@Preview
private fun CircleAvatarComponentPreview() {
    AppTheme {
        CircleAvatarComponent(
            size = 100.dp,
            imageUrl = ImageMock.image,
        )
    }
}