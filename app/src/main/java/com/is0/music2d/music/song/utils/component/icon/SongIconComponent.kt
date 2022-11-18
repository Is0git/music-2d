package com.is0.music2d.music.song.utils.component.icon

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SongIconComponent(
    modifier: Modifier,
    imageVector: ImageVector,
    contentDescription: String,
) {
    Icon(
        modifier = modifier,
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}