package com.is0.music2d.music.song.utils.component.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.is0.music2d.R
import com.is0.music2d.music.song.utils.component.SongIconComponent

@Composable
fun SongDurationIconComponent(
    modifier: Modifier = Modifier,
) {
    SongIconComponent(
        modifier = modifier,
        imageVector = Icons.Filled.AccessTime,
        contentDescription = stringResource(R.string.song_duration_image_content_description),
    )
}