package com.is0.music2d.main.home.my_music.category.utils.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.is0.music2d.R
import com.is0.music2d.music.album.artist.utils.component.ArtistAvatarComponent
import com.is0.music2d.music.album.artist.utils.data.domain.Artist
import com.is0.music2d.music.album.artist.utils.data.domain.ArtistMock
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.domain.SongSize
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.gradient.scrim.ScrimComponent
import com.is0.music2d.utils.composable.image.avatar.ImageComponent
import com.is0.music2d.utils.composable.padding.HorizontalSpacerComponent
import com.is0.music2d.utils.composable.padding.VerticalSpacerComponent
import com.is0.music2d.utils.composable.text.LabelLargeTextComponent
import com.is0.music2d.utils.composable.text.LabelMediumTextComponent
import com.is0.music2d.utils.composable.text.LabelSmallTextComponent
import com.is0.music2d.utils.data.mock.ImageMock
import com.is0.music2d.utils.size.FileSizeFormatter

@Composable
fun CategorySongItemComponent(
    modifier: Modifier = Modifier,
    song: Song,
    onSongSizeFormat: (songSize: SongSize) -> String,
    onSongDurationFormat: (durationMillis: Long) -> String,
) {
    val formattedSongSize = remember(song.songSize) {
        onSongSizeFormat(song.songSize)
    }
    val formattedSongDuration = remember(song.durationMillis) {
        onSongDurationFormat(song.durationMillis)
    }

    CategorySongItemContentComponent(
        modifier = modifier,
        songDurationText = formattedSongDuration,
        songSizeText = formattedSongSize,
        songImageUrl = song.imageUrl,
        songName = song.title,
        artist = song.artist,
    )
}

@Composable
private fun CategorySongItemContentComponent(
    modifier: Modifier = Modifier,
    songSizeText: String,
    songImageUrl: String,
    songDurationText: String,
    songName: String,
    artist: Artist,
) {
    Column(modifier = modifier) {
        SongCoverComponent(
            songSizeText = songSizeText,
            songImageUrl = songImageUrl,
            songDurationText = songDurationText,
        )
        VerticalSpacerComponent(height = 8.dp)
        SongInfoComponent(
            songName = songName,
            artist = artist,
        )
    }
}

@Composable
private fun SongCoverComponent(
    modifier: Modifier = Modifier,
    songSizeText: String = "",
    songImageUrl: String = "",
    songDurationText: String = "",
) {
    Box(
        modifier = modifier
            .heightIn(100.dp, 150.dp)
            .aspectRatio(4 / 3f)
            .clip(AppTheme.shapes.songCoverShape)
    ) {
        SongImageComponent(
            modifier = Modifier.fillMaxSize(),
            songImageUrl = songImageUrl,
        )
        SongCoverScrimComponent()
        SongCoverInfoComponent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(8.dp),
            songSizeText = songSizeText,
            songDurationText = songDurationText,
        )
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

@Composable
private fun SongCoverInfoComponent(
    modifier: Modifier = Modifier,
    songSizeText: String = "",
    songDurationText: String = "",
) {
    CompositionLocalProvider(LocalContentColor provides AppTheme.colors.onPrimaryContainerColor) {
        Row(
            modifier = modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            SongDurationComponent(
                modifier = Modifier.weight(1f),
                duration = songDurationText,
            )
            VerticalSpacerComponent(height = AppTheme.dimensions.smallComponentGap)
            Box(modifier = Modifier.weight(1f)) {
                SongSizeComponent(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    sizeText = songSizeText,
                )
            }
        }
    }
}

@Composable
private fun SongDurationComponent(
    modifier: Modifier = Modifier,
    duration: String = ""
) {
    SongCoverElementComponent(
        modifier = modifier,
        text = {
            LabelSmallTextComponent(
                modifier = modifier,
                text = duration,
            )
        },
        icon = {
            SongDurationIconComponent()
        }
    )
}

@Composable
private fun SongDurationIconComponent(
    modifier: Modifier = Modifier,
) {
    SongCoverIconComponent(
        modifier = modifier,
        imageVector = Icons.Filled.AccessTime,
        contentDescription = stringResource(R.string.song_duration_image_content_description),
    )
}

@Composable
private fun SongSizeComponent(
    modifier: Modifier = Modifier,
    sizeText: String = "",
    textAlign: TextAlign? = null,
) {
    SongCoverElementComponent(
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

@Composable
private fun SongSizeIconComponent(modifier: Modifier = Modifier) {
    SongCoverIconComponent(
        modifier = modifier,
        imageVector = Icons.Filled.Storage,
        contentDescription = stringResource(R.string.song_size_icon_content_description),
    )
}

@Composable
private fun SongCoverIconComponent(
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

@Composable
private fun SongCoverElementComponent(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {},
    text: @Composable () -> Unit = {},
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallComponentGap),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        text()
    }
}

@Composable
private fun SongInfoComponent(
    modifier: Modifier = Modifier,
    songName: String,
    artist: Artist,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        SongArtistAvatarComponent(
            aristImageUrl = artist.artistImageUrl,
            artistId = artist.id,
        )
        HorizontalSpacerComponent(width = AppTheme.dimensions.smallComponentGap)
        SongInfoLiteralComponent(
            songName = songName,
            songArtistName = artist.name,

            )
    }
}

@Composable
private fun SongArtistAvatarComponent(
    modifier: Modifier = Modifier,
    aristImageUrl: String,
    artistId: String,
) {
    ArtistAvatarComponent(
        modifier = modifier,
        artistImageUrl = aristImageUrl,
        key = aristImageUrl + artistId,
    )
}

@Composable
private fun SongInfoLiteralComponent(
    modifier: Modifier = Modifier,
    songName: String,
    songArtistName: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallComponentGap),
    ) {
        SongNameComponent(name = songName)
        SongAristNameComponent(artistName = songArtistName)
    }
}

@Composable
private fun SongNameComponent(
    modifier: Modifier = Modifier,
    name: String,
) {
    LabelLargeTextComponent(
        modifier = modifier,
        text = name,
        lines = 2,
        color = AppTheme.colors.onBackgroundColor,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
private fun SongAristNameComponent(
    modifier: Modifier = Modifier,
    artistName: String,
) {
    LabelMediumTextComponent(
        modifier = modifier,
        text = artistName,
        color = AppTheme.colors.onBackgroundColorVariant,
        lines = 1,
        overflow = TextOverflow.Ellipsis,
    )
}

@Composable
@Preview
private fun CategorySongItemContentComponentPreview() {
    AppTheme {
        CategorySongItemContentComponent(
            songSizeText = "24.3 MB",
            songDurationText = "3 min 2 s",
            songImageUrl = ImageMock.randomImage,
            artist = ArtistMock.randomArtist,
            songName = "Pieces"
        )
    }
}