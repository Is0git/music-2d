package com.is0.music2d.utils.composable.duration

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.is0.music2d.music.song.utils.component.icon.SongDurationIconComponent
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.layout.LabelLayoutComponent
import com.is0.music2d.utils.composable.text.LabelSmallTextComponent

@Composable
fun DurationComponent(
    modifier: Modifier = Modifier,
    durationText: String = "",
    textColor: Color = Color.Unspecified,
    gap: Dp = AppTheme.dimensions.smallComponentGap,
) {
    LabelLayoutComponent(
        modifier = modifier,
        gap = gap,
        text = {
            LabelSmallTextComponent(
                modifier = modifier,
                color = textColor,
                text = durationText,
            )
        },
        icon = {
            SongDurationIconComponent()
        },
    )
}