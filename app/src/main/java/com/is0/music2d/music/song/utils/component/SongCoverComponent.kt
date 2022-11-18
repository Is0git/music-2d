package com.is0.music2d.music.song.utils.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.gradient.scrim.ScrimComponent
import com.is0.music2d.utils.composable.image.avatar.ImageComponent

@Composable
fun SongCoverComponent(
    modifier: Modifier = Modifier,
    songImageUrl: String = "",
    songInfoComponent: @Composable BoxScope.() -> Unit = {},
    icon: @Composable () -> Unit = {},
) {
    Box(
        modifier = modifier.clip(AppTheme.shapes.songCoverShape)
    ) {
        SongImageComponent(
            modifier = Modifier.fillMaxSize(),
            songImageUrl = songImageUrl,
        )
        SongCoverScrimComponent()
        songInfoComponent()
        Box(modifier = Modifier.align(Alignment.TopEnd)) {
            icon()
        }
    }
}

@Composable
private fun SongImageComponent(
    modifier: Modifier = Modifier,
    songImageUrl: String = "",
) {
    ImageComponent(
        modifier = modifier,
        imageUrl = songImageUrl,
    )
}

@Composable
private fun SongCoverScrimComponent(
    modifier: Modifier = Modifier,
) {
    ScrimComponent(
        modifier = modifier,
        color = AppTheme.colors.imageScrimColor,
    )
}
