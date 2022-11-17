package com.is0.music2d.main.home.library.storage.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.is0.music2d.main.home.library.storage.preview.utils.component.StorageItemComponent
import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.music.song.storage.composable.StorageProviders
import com.is0.music2d.music.song.utils.component.local.LocalSongStorageTypeFormatter
import com.is0.music2d.utils.composable.error.handleSnackbarError
import com.is0.music2d.utils.composable.local.LocalDurationFormatter

@Composable
fun StorageSongSelectionScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageSongsPreviewScreenViewModel = hiltViewModel(),
) {
    val storageSongsPreviews by viewModel.storageSongsPreview.observeAsState(emptyList())

    val durationFormatter = LocalDurationFormatter.current

    viewModel.error.handleSnackbarError()

    StorageProviders {
        StorageSongSelectionContentComponent(
            modifier = modifier,
            storageSongsPreview = storageSongsPreviews,
            onTotalDurationFormat = durationFormatter::formatDuration,
        )
    }
}

@Composable
private fun StorageSongSelectionContentComponent(
    modifier: Modifier = Modifier,
    storageSongsPreview: List<StorageSongsPreview> = emptyList(),
    onTotalDurationFormat: (totalDuration: Long) -> String = { "" },
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        storageSongsPreview.forEach { storageSongPreview ->
            StorageItemComponent(
                modifier = Modifier.fillMaxWidth(),
                durationText = onTotalDurationFormat(storageSongPreview.totalDurationMillis),
                title = LocalSongStorageTypeFormatter.current.formatStorageType(storageSongPreview.songStorageType),
            )
        }
    }
}