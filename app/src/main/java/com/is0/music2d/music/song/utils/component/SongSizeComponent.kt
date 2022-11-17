package com.is0.music2d.music.song.utils.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.is0.music2d.music.song.utils.component.icon.SongSizeIconComponent
import com.is0.music2d.utils.composable.layout.LabelLayoutComponent
import com.is0.music2d.utils.composable.text.LabelSmallTextComponent

@Composable
fun SongSizeComponent(
    modifier: Modifier = Modifier,
    sizeText: String = "",
    textAlign: TextAlign? = null,
) {
    LabelLayoutComponent(
        modifier = modifier,
        text = {
            LabelSmallTextComponent(
                text = sizeText,
                textAlign = textAlign,
            )
        },
        icon = {
            SongSizeIconComponent()
        }
    )
}