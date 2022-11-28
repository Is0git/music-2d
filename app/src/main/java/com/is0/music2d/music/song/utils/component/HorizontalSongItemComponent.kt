package com.is0.music2d.music.song.utils.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.is0.music2d.R
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.domain.SongMock
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.duration.DurationComponent
import com.is0.music2d.utils.composable.modifier.placeholder
import com.is0.music2d.utils.composable.text.LabelLargeTextComponent
import com.is0.music2d.utils.composable.text.LabelMediumTextComponent
import com.is0.music2d.utils.data.mock.ImageMock

@Composable
fun HorizontalSongItemComponent(
    modifier: Modifier = Modifier,
    song: Song,
    songDurationText: String,
    songSizeText: String,
    songImageUrl: String,
    isLoading: Boolean = false,
    action: @Composable () -> Unit = {},
) {
    HorizontalSongItemContentComponent(
        modifier = modifier.padding(bottom = 2.dp),
        song = song,
        songDurationText = songDurationText,
        songSizeText = songSizeText,
        songImageUrl = songImageUrl,
        action = action,
        isLoading = isLoading,
    )
}

@Composable
private fun HorizontalSongItemContentComponent(
    modifier: Modifier = Modifier,
    song: Song,
    songDurationText: String,
    songSizeText: String,
    songImageUrl: String,
    action: @Composable () -> Unit = {},
    isLoading: Boolean,
) {
    Surface(modifier = modifier) {
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(
                AppTheme.dimensions.largeComponentGap,
            )
        ) {
            SmallSongCoverComponent(
                songImageUrl = songImageUrl,
                isLoading = isLoading,
            )
            SongItemInfoComponent(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 4.dp),
                songName = song.title,
                artistName = song.artist.name,
                songDurationText = songDurationText,
                songSizeText = songSizeText,
                isLoading = isLoading,
            )
            action()
        }
    }
}

@Composable
private fun SmallSongCoverComponent(
    modifier: Modifier = Modifier,
    songImageUrl: String = "",
    isLoading: Boolean,
) {
    SongCoverComponent(
        modifier = modifier.requiredSize(AppTheme.dimensions.smallImageSize),
        songImageUrl = songImageUrl,
        isLoading = isLoading,
    )
}

@Composable
private fun SongItemInfoComponent(
    modifier: Modifier = Modifier,
    artistName: String,
    songName: String,
    songDurationText: String,
    songSizeText: String,
    isLoading: Boolean,
) {
    val placeholderModifier = Modifier.placeholder(
        visible = isLoading,
        shape = AppTheme.shapes.songCoverShape,
    )

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallComponentGap),
    ) {
        SongItemLabelComponent(
            modifier = placeholderModifier,
            songName = songName,
            artistName = artistName,
        )
        SongItemSubtitleComponent(
            modifier = placeholderModifier,
            durationText = songDurationText,
            sizeText = songSizeText,
        )
    }
}

@Composable
private fun SongItemLabelComponent(
    modifier: Modifier = Modifier,
    songName: String,
    artistName: String,
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        LabelLargeTextComponent(
            modifier = modifier,
            color = AppTheme.colors.onBackgroundColor,
            text = songName,
            overflow = TextOverflow.Ellipsis,
            lines = 1,
        )
        LabelMediumTextComponent(
            modifier = modifier,
            color = AppTheme.colors.onBackgroundColorVariant,
            text = stringResource(
                id = R.string.horizontal_song_item_artist_format,
                artistName,
            ),
            overflow = TextOverflow.Ellipsis,
            lines = 1,
        )
    }
}

@Composable
private fun SongItemSubtitleComponent(
    modifier: Modifier = Modifier,
    durationText: String = "",
    sizeText: String = "",
) {
    CompositionLocalProvider(LocalContentColor provides AppTheme.colors.onBackgroundColorVariant) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.mediumComponentGap),
        ) {
            val rowModifier = Modifier.weight(1f, false)
            DurationComponent(
                modifier = rowModifier,
                durationText = durationText,
            )
            SongSizeComponent(
                modifier = rowModifier,
                sizeText = sizeText,
            )
        }
    }
}

@Composable
@Preview
fun HorizontalSongItemContentComponentPreview() {
    AppTheme {
        HorizontalSongItemContentComponent(
            song = SongMock.generateRandomSong(),
            songDurationText = "3 h 2m",
            songSizeText = "43.2MB",
            songImageUrl = ImageMock.randomImage,
            isLoading = false,
        )
    }
}