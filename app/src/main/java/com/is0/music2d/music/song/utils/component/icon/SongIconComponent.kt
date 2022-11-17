package com.is0.music2d.music.song.utils.component

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun SongIconComponent(
    modifier: Modifier,
    imageVector: ImageVector,
    contentDescription: String,
) {
    Icon(
        modifier = modifier.requiredSize(10.dp),
        imageVector = imageVector,
        contentDescription = contentDescription
    )
}