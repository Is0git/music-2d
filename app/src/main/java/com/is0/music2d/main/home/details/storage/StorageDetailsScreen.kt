package com.is0.music2d.main.home.details.storage

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.is0.music2d.main.home.details.storage.filesystem.FileSystemStorageDetailsViewModel
import com.is0.music2d.main.home.details.storage.memory.MemoryStorageDetailsViewModel
import com.is0.music2d.main.home.details.storage.utils.component.OnSongSaveClick
import com.is0.music2d.main.home.details.storage.utils.component.StorageSongItemComponent
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetails
import com.is0.music2d.main.home.details.utils.component.DetailsScreenComponent
import com.is0.music2d.music.song.storage.utils.composable.StorageProviders
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
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
    storageType: SongStorageType,
    viewModel: StorageDetailsViewModel = createStorageDetailsViewModel(storageType),
    navController: NavController = rememberNavController(),
) {
    val storageDetails by viewModel.storageDetails.observeAsState(null)
    val isLoading by viewModel.isLoading.observeAsState(false)

    val songDurationFormatter = LocalDurationFormatter.current
    val songSizeFormatter = LocalSizeFormatter.current

    StorageProviders {
        BaseScaffoldComponent(
            modifier = modifier,
            baseViewModel = viewModel,
            onNavigateUp = navController::popBackStack,
            title = LocalSongStorageTypeFormatter.current.formatStorageType(storageType),
        ) { padding ->
            StorageDetailsContentComponent(
                modifier = Modifier.padding(padding),
                storageDetails = storageDetails,
                formatDuration = { duration -> songDurationFormatter.formatDuration(duration, false) },
                formatFileSize = { size -> songSizeFormatter.formatSize(size = size) },
                isLoading = isLoading,
                storageType = storageType,
                onSongSaveClick = viewModel::toggleSavedSong,
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
    onSongSaveClick: OnSongSaveClick = {},
) {
    DetailsScreenComponent(
        modifier = modifier,
        items = storageDetails?.songs,
        isLoading = isLoading
    ) { detailsSong ->
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
}

@Composable
private fun createStorageDetailsViewModel(songStorageType: SongStorageType) =
    when (songStorageType) {
        SongStorageType.MEMORY -> hiltViewModel<MemoryStorageDetailsViewModel>()
        SongStorageType.FILESYSTEM -> hiltViewModel<FileSystemStorageDetailsViewModel>()
        SongStorageType.NONE -> error("ViewModel does not exist for this storage type: $songStorageType")
    }
