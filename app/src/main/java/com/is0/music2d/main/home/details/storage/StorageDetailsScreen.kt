package com.is0.music2d.main.home.details.storage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.is0.music2d.main.home.details.storage.utils.component.OnSongSaveClick
import com.is0.music2d.main.home.details.storage.utils.component.StorageSongItemComponent
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetails
import com.is0.music2d.main.home.details.utils.component.DetailsScreenComponent
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.composable.StorageProviders
import com.is0.music2d.music.song.utils.component.local.LocalSongStorageTypeFormatter
import com.is0.music2d.music.song.utils.data.domain.toSize
import com.is0.music2d.music.song.utils.formatter.FormatSongDuration
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent
import com.is0.music2d.utils.size.FormatFileSize

@Composable
fun StorageDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: StorageDetailsViewModel,
    storageType: SongStorageType,
    navController: NavController = rememberNavController(),
) {
    val storageDetails by viewModel.storageDetails.observeAsState(null)
    val isLoading by viewModel.isLoading.observeAsState(false)

    val songDurationFormatter = LocalDurationFormatter.current
    val songSizeFormatter = LocalSizeFormatter.current

    StorageProviders {
        val songStorageTypeFormatter = LocalSongStorageTypeFormatter.current

        BaseScaffoldComponent(
            modifier = modifier,
            baseViewModel = viewModel,
            onNavigateUp = navController::popBackStack,
            title = LocalSongStorageTypeFormatter.current.formatStorageType(storageType),
        ) { padding ->
            StorageDetailsContentComponent(
                modifier = Modifier.padding(padding),
                storageDetails = storageDetails,
                formatDuration = { duration -> songDurationFormatter.formatDuration(duration) },
                formatFileSize = { size -> songSizeFormatter.formatSize(size = size) },
                onSongSaveClick = viewModel::toggleSavedSong,
                isLoading = isLoading,
                storageType = storageType,
                title = songStorageTypeFormatter.formatStorageType(storageType),
            )
        }
    }
}

@Composable
fun StorageDetailsContentComponent(
    modifier: Modifier = Modifier,
    storageDetails: StorageDetails?,
    formatDuration: FormatSongDuration,
    formatFileSize: FormatFileSize,
    isLoading: Boolean = false,
    storageType: SongStorageType = SongStorageType.NONE,
    title: String,
    onSongSaveClick: OnSongSaveClick = {},
) {
    DetailsScreenComponent(
        modifier = modifier,
        items = storageDetails?.songs,
        images = storageDetails?.previewImages.orEmpty(),
        headerTitle = title,
        isLoading = isLoading,
        durationText = storageDetails?.totalDuration?.let { formatDuration(it) }.orEmpty(),
        songCount = storageDetails?.songCount ?: 0,
        itemContent = { detailsSong ->
            StorageSongItemComponent(
                modifier = Modifier.fillMaxWidth(),
                detailsSong = detailsSong,
                songDurationText = formatDuration(detailsSong.song.durationMillis),
                songSizeText = formatFileSize(detailsSong.song.songSize.toSize()),
                songImageUrl = detailsSong.song.imageUrl,
                onSongSaveClick = onSongSaveClick,
                storageType = storageType,
            )
        }
    )
}
