package com.is0.music2d.main.home.details.song

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.is0.music2d.main.home.details.song.utils.data.domain.SongDetails
import com.is0.music2d.main.home.details.song.utils.data.domain.SongDetailsMock
import com.is0.music2d.music.playback.PlaybackControlsComponent
import com.is0.music2d.music.song.utils.component.SongCoverComponent
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.padding.VerticalSpacerComponent
import com.is0.music2d.utils.composable.text.HeadlineMediumTextComponent
import com.is0.music2d.utils.composable.text.LabelMediumTextComponent

@Composable
fun SongDetailsScreen(
    modifier: Modifier = Modifier,
    song: Song,
    songDetailsViewModel: SongDetailsViewModel = hiltViewModel(),
) {
    songDetailsViewModel.watchSongDetails(song)

    val songDetails by songDetailsViewModel.songDetails.observeAsState()

    SongDetailsContentComponent(
        modifier = modifier,
        songDetails = songDetails,
    )
}

@Composable
private fun SongDetailsContentComponent(
    modifier: Modifier = Modifier,
    songDetails: SongDetails?,
    isLoading: Boolean = false,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(AppTheme.dimensions.bodyMarginMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(48.dp),
    ) {
        SongInfoComponent(
            modifier = Modifier.weight(1f),
            isLoading = isLoading,
            songDetails = songDetails,
        )
        PlaybackControlsComponent()
    }
}

@Composable
private fun SongInfoComponent(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
    songDetails: SongDetails?
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(
            space = AppTheme.dimensions.largeComponentGap,
            alignment = Alignment.CenterVertically,
        ),
    ) {
        SongArtistTitleComponent(
            artistName = songDetails?.artist?.name.orEmpty(),
        )
        SongDetailsCoverComponent(
            modifier = Modifier
                .aspectRatio(1.2f)
                .weight(1f),
            isLoading = isLoading,
            songImageUrl = songDetails?.imageUrl.orEmpty(),
        )
        SongLiteralInfoComponent(
            albumTitle = songDetails?.songTitle.orEmpty(),
            songTitle = songDetails?.albumTitle.orEmpty(),
        )
    }
}

@Composable
private fun SongArtistTitleComponent(
    modifier: Modifier = Modifier,
    artistName: String,
) {
    LabelMediumTextComponent(
        modifier = modifier,
        text = artistName,
        color = AppTheme.colors.onBackgroundColor,
    )
}

@Composable
private fun SongDetailsCoverComponent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    songImageUrl: String = "",
) {
    Box(modifier = modifier) {
        SongCoverComponent(
            modifier = Modifier
                .graphicsLayer {
                    translationY = 50f
                    scaleY = 1.3f
                    scaleX = 1.1f
                }
                .blur(
                    radius = 16.dp,
                    edgeTreatment = BlurredEdgeTreatment.Unbounded,
                ),
            isLoading = isLoading,
            songImageUrl = songImageUrl,
        )
        SongCoverComponent(
            isLoading = isLoading,
            songImageUrl = songImageUrl,
        )
    }
}

@Composable
private fun SongLiteralInfoComponent(
    modifier: Modifier = Modifier,
    albumTitle: String,
    songTitle: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SongAlbumComponent(
            albumTitle = albumTitle,
        )
        VerticalSpacerComponent(height = AppTheme.dimensions.mediumComponentGap)
        SongTitleComponent(
            songTitle = songTitle,
        )
    }
}

@Composable
private fun SongAlbumComponent(
    modifier: Modifier = Modifier,
    albumTitle: String,
) {
    LabelMediumTextComponent(
        modifier = modifier,
        text = albumTitle,
        color = AppTheme.colors.onBackgroundVariantColor,
    )
}

@Composable
private fun SongTitleComponent(
    modifier: Modifier = Modifier,
    songTitle: String,
) {
    HeadlineMediumTextComponent(
        modifier = modifier,
        text = songTitle,
        color = AppTheme.colors.onBackgroundColor,
        textAlign = TextAlign.Center,
    )
}

@Composable
@Preview
private fun SongDetailsContentComponentPreview() {
    AppTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppTheme.colors.backgroundVariantColor),
        ) {
            SongDetailsContentComponent(
                songDetails = SongDetailsMock.songDetails,
            )
        }
    }
}



