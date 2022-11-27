package com.is0.music2d.main.home.library.category.utils.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.is0.music2d.music.song.storage.utils.composable.SavedSongsMenuComponent
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.music.song.utils.component.SongCoverComponent
import com.is0.music2d.music.song.utils.component.SongSizeComponent
import com.is0.music2d.music.song.utils.component.local.LocalSongStorageTypeFormatter
import com.is0.music2d.music.song.utils.data.domain.SongMock
import com.is0.music2d.music.song.utils.data.domain.SongSize
import com.is0.music2d.music.utils.component.ArtistAvatarComponent
import com.is0.music2d.music.utils.data.domain.Artist
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.duration.DurationComponent
import com.is0.music2d.utils.composable.icon.StorageIconComponent
import com.is0.music2d.utils.composable.padding.HorizontalSpacerComponent
import com.is0.music2d.utils.composable.padding.VerticalSpacerComponent
import com.is0.music2d.utils.composable.text.LabelLargeTextComponent
import com.is0.music2d.utils.composable.text.LabelMediumTextComponent

@Composable
fun CategorySongItemComponent(
    modifier: Modifier = Modifier,
    storedSong: StoredSong,
    onSongSizeFormat: (songSize: SongSize) -> String,
    onSongDurationFormat: (durationMillis: Long) -> String,
    onSongStorageSelected: (songId: String, storageType: SongStorageType) -> Unit,
    availableSongStorageTypes: List<SongStorageType> = listOf(),
) {
    val song = storedSong.song

    val formattedSongSize = remember(song.songSize) {
        onSongSizeFormat(song.songSize)
    }
    val formattedSongDuration = remember(song.durationMillis) {
        onSongDurationFormat(song.durationMillis)
    }

    val songStorageTypeFormatter = LocalSongStorageTypeFormatter.current

    CategorySongItemContentComponent(
        modifier = modifier,
        songSizeText = formattedSongSize,
        songDurationText = formattedSongDuration,
        songStorageTypes = storedSong.songStorageTypes,
        formatSongStorageType = songStorageTypeFormatter::formatStorageType,
        onSongStorageSelected = onSongStorageSelected,
        availableSongStorageTypes = availableSongStorageTypes,
        storedSong = storedSong,
    )
}

@Composable
private fun CategorySongItemContentComponent(
    modifier: Modifier = Modifier,
    songSizeText: String,
    songDurationText: String,
    storedSong: StoredSong,
    availableSongStorageTypes: List<SongStorageType> = listOf(),
    formatSongStorageType: (storageType: SongStorageType) -> String,
    onSongStorageSelected: (songId: String, storageType: SongStorageType) -> Unit,
    songStorageTypes: List<SongStorageType> = emptyList(),
) {
    Column(modifier = modifier.width(IntrinsicSize.Min)) {
        CategorySongCoverComponent(
            modifier = Modifier
                .requiredHeight(150.dp)
                .aspectRatio(4 / 3f),
            songSizeText = songSizeText,
            songImageUrl = storedSong.song.imageUrl,
            songDurationText = songDurationText,
            songStorageTypes = songStorageTypes,
            categoryMenu = {
                Box {
                    SavedSongsMenuComponent(
                        formatSongStorageType = formatSongStorageType,
                        savedSongStorageTypes = storedSong.songStorageTypes,
                        availableSongStorageTypes = availableSongStorageTypes,
                        onSongStorageSelected = onSongStorageSelected,
                        songId = storedSong.song.id,
                    )
                }
            },
        )
        VerticalSpacerComponent(height = 8.dp)
        SongInfoComponent(
            songName = storedSong.song.title,
            artist = storedSong.song.artist,
        )
    }
}

@Composable
private fun CategorySongCoverComponent(
    modifier: Modifier = Modifier,
    songSizeText: String = "",
    songImageUrl: String = "",
    songDurationText: String = "",
    songStorageTypes: List<SongStorageType> = emptyList(),
    categoryMenu: @Composable () -> Unit = {},
) {
    SongCoverComponent(
        modifier = modifier,
        songImageUrl = songImageUrl,
        songInfoComponent = {
            SongCoverInfoComponent(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                songSizeText = songSizeText,
                songDurationText = songDurationText,
            )
        },
        menu = categoryMenu,
        icon = {
            if (songStorageTypes.isNotEmpty()) {
                Row(
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(AppTheme.colors.surfaceColor.copy(alpha = 0.40f))
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.smallComponentGap),
                ) {
                    songStorageTypes.forEach { type ->
                        StorageIconComponent(storageType = type)
                    }
                }
            }
        }
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
            DurationComponent(
                modifier = Modifier.weight(1f),
                durationText = songDurationText,
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
        color = AppTheme.colors.onSurfaceColor,
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
            storedSong = StoredSong(SongMock.generateRandomSong(), emptyList()),
            onSongStorageSelected = { _, _ -> },
            availableSongStorageTypes = allSongStorageTypes(),
            formatSongStorageType = { "" },
            songStorageTypes = emptyList(),
        )
    }
}