package com.is0.music2d.music.song.utils.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.music.song.utils.component.icon.SongDurationIconComponent
import com.is0.music2d.utils.composable.layout.LabelLayoutComponent
import com.is0.music2d.utils.composable.text.LabelSmallTextComponent

@Composable
fun SongDurationComponent(
    modifier: Modifier = Modifier,
    duration: String = ""
) {
    LabelLayoutComponent(
        modifier = modifier,
        text = {
            LabelSmallTextComponent(
                modifier = modifier,
                text = duration,
            )
        },
        icon = {
            SongDurationIconComponent()
        },
    )
}