package com.is0.music2d.music.utils.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.image.avatar.CircleAvatarComponent

@Composable
fun ArtistAvatarComponent(
    modifier: Modifier = Modifier,
    artistImageUrl: String,
    key: String? = null,
    isLoading: Boolean = false,
) {
    CircleAvatarComponent(
        modifier = modifier,
        imageUrl = artistImageUrl,
        key = key,
        size = AppTheme.dimensions.avatarSize,
        borderColor = AppTheme.colors.secondaryColor,
        borderSize = 2.dp,
        isLoading = isLoading,
    )
}