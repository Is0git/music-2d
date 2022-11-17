package com.is0.music2d.main.home.library.storage.preview

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.main.home.library.storage.preview.utils.component.StorageItemComponent
import com.is0.music2d.main.home.library.storage.preview.utils.data.domain.StorageSongsPreview
import com.is0.music2d.music.song.storage.composable.StorageProviders
import com.is0.music2d.music.song.utils.component.local.LocalSongStorageTypeFormatter

@Composable
fun StorageSongSelectionScreen(
    modifier: Modifier = Modifier,
) {
    StorageProviders {
        StorageSongSelectionContentComponent()
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
                durationText = onTotalDurationFormat(storageSongPreview.totalDurationMillis),
                title = LocalSongStorageTypeFormatter.current.formatStorageType(storageSongPreview.songStorageType),
            )
        }
    }
}