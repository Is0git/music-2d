package com.is0.music2d.main.home.details.storage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.is0.music2d.main.home.details.storage.utils.component.OnSongSaveClick
import com.is0.music2d.main.home.details.storage.utils.component.StorageSongItemComponent
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSongMock
import com.is0.music2d.music.song.utils.data.domain.toSize
import com.is0.music2d.music.song.utils.formatter.FormatSongDuration
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent
import com.is0.music2d.utils.size.FormatFileSize

@Composable
fun StorageDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageDetailsViewModel,
) {
    val storageDetailsSongs by viewModel.storageSongs.observeAsState(emptyList())

    val songDurationFormatter = LocalDurationFormatter.current
    val songSizeFormatter = LocalSizeFormatter.current

    BaseScaffoldComponent(
        modifier = modifier,
        baseViewModel = viewModel,
    ) {
        StorageDetailsContentComponent(
            songs = storageDetailsSongs,
            formatSongDuration = { duration -> songDurationFormatter.formatDuration(duration) },
            formatFileSize = { size -> songSizeFormatter.formatSize(size = size) },
            onSongSaveClick = viewModel::toggleSavedSong,
        )
    }
}

@Composable
fun StorageDetailsContentComponent(
    modifier: Modifier = Modifier,
    songs: List<StorageDetailsSong>,
    formatSongDuration: FormatSongDuration,
    formatFileSize: FormatFileSize,
    onSongSaveClick: OnSongSaveClick = { _, _ -> },
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(songs) { detailsSong ->
            StorageSongItemComponent(
                modifier = Modifier.fillMaxWidth(),
                detailsSong = detailsSong,
                songDurationText = formatSongDuration(detailsSong.song.durationMillis),
                songSizeText = formatFileSize(detailsSong.song.songSize.toSize()),
                songImageUrl = detailsSong.song.imageUrl,
                onSongSaveClick = onSongSaveClick,
            )
        }
    }
}

@Composable
@Preview
private fun StorageDetailsContentComponentPreview() {
    AppTheme {
        StorageDetailsContentComponent(
            songs = StorageDetailsSongMock.songs,
            formatSongDuration = { "24h 3m" },
            formatFileSize = { "42MB" },
        )
    }
}
