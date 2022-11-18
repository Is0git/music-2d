package com.is0.music2d.music.song.utils.component.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storage
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.is0.music2d.R

@Composable
fun SongSizeIconComponent(modifier: Modifier = Modifier) {
    SongIconComponent(
        modifier = modifier,
        imageVector = Icons.Filled.Storage,
        contentDescription = stringResource(R.string.song_size_icon_content_description),
    )
}