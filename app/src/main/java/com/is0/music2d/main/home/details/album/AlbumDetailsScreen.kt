package com.is0.music2d.main.home.details.album

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.utils.component.SongsDetailsHeaderComponent
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.utils.component.HorizontalSongItemComponent
import com.is0.music2d.music.song.utils.data.domain.SongMock
import com.is0.music2d.music.song.utils.data.domain.toSize
import com.is0.music2d.music.song.utils.formatter.FormatSongDuration
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.OverflowIconButtonComponent
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent
import com.is0.music2d.utils.size.FormatFileSize

@Composable
fun AlbumDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumDetailsViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {
    val albumDetails by viewModel.albumDetails.observeAsState(AlbumDetails.empty())

    val songDurationFormatter = LocalDurationFormatter.current
    val songSizeFormatter = LocalSizeFormatter.current

    BaseScaffoldComponent(
        modifier = modifier,
        baseViewModel = viewModel,
        title = albumDetails.name,
        onNavigateUp = navController::popBackStack,
    ) { padding ->
        AlbumDetailsContentComponent(
            modifier = Modifier.padding(padding),
            albumDetails = albumDetails,
            formatSongDuration = { duration -> songDurationFormatter.formatDuration(duration) },
            formatFileSize = { size -> songSizeFormatter.formatSize(size = size) },
        )
    }
}

@Composable
private fun AlbumDetailsContentComponent(
    modifier: Modifier = Modifier,
    albumDetails: AlbumDetails,
    formatSongDuration: FormatSongDuration,
    formatFileSize: FormatFileSize,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        item {
            SongsDetailsHeaderComponent(
                images = albumDetails.storedSong.take(5).map { it.song.imageUrl },
                title = albumDetails.name
            )
        }
        items(albumDetails.storedSong) { storedSong ->
            HorizontalSongItemComponent(
                modifier = Modifier.fillMaxWidth(),
                song = storedSong.song,
                songDurationText = formatSongDuration(storedSong.song.durationMillis),
                songSizeText = formatFileSize(storedSong.song.songSize.toSize()),
                songImageUrl = storedSong.song.imageUrl,
                action = { OverflowIconButtonComponent() },
            )
        }
    }
}

@Composable
@Preview
private fun AlbumDetailsContentComponentPreview() {
    AppTheme {
        AlbumDetailsContentComponent(
            albumDetails = AlbumDetails(
                "Test",
                storedSong = SongMock.generateSongs(40).map { StoredSong(it, emptyList()) },
            ),
            formatSongDuration = { "2h 3m" },
            formatFileSize = { "12MB" },
        )
    }
}