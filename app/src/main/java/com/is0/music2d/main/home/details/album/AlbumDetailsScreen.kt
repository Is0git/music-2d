package com.is0.music2d.main.home.details.album

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.utils.component.DetailsScreenComponent
import com.is0.music2d.music.song.storage.utils.composable.SaveStorageIconButtonComponent
import com.is0.music2d.music.song.storage.utils.composable.StorageProviders
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.music.song.utils.component.HorizontalSongItemComponent
import com.is0.music2d.music.song.utils.component.local.LocalSongStorageTypeFormatter
import com.is0.music2d.music.song.utils.data.domain.SongMock
import com.is0.music2d.music.song.utils.data.domain.SongSize
import com.is0.music2d.music.song.utils.data.domain.toSize
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.OverflowIconButtonComponent
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent
import com.is0.music2d.utils.composable.text.TitleSmallTextComponent

@Composable
fun AlbumDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumDetailsViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {
    val albumDetails by viewModel.albumDetails.observeAsState(AlbumDetails.empty())

    val songDurationFormatter = LocalDurationFormatter.current
    val songSizeFormatter = LocalSizeFormatter.current

    StorageProviders {
        val songStorageTypeFormatter = LocalSongStorageTypeFormatter.current

        BaseScaffoldComponent(
            modifier = modifier,
            baseViewModel = viewModel,
            title = albumDetails.name,
            onNavigateUp = navController::popBackStack,
        ) { padding ->
            AlbumDetailsContentComponent(
                modifier = Modifier.padding(padding),
                albumDetails = albumDetails,
                formatSongSize = { songSize -> songSizeFormatter.formatSize(songSize.toSize()) },
                formatSongDuration = songDurationFormatter::formatDuration,
                formatSongStorage = songStorageTypeFormatter::formatStorageType,
                availableSongStorageTypes = viewModel.availableSongStorageTypes,
                onSongStorageSelected = viewModel::toggleSavedSong,
            )
        }
    }
}

@Composable
private fun AlbumDetailsContentComponent(
    modifier: Modifier = Modifier,
    albumDetails: AlbumDetails,
    formatSongDuration: (duration: Long) -> String,
    formatSongSize: (size: SongSize) -> String,
    formatSongStorage: (storageType: SongStorageType) -> String,
    availableSongStorageTypes: List<SongStorageType> = emptyList(),
    onSongStorageSelected: (songId: String, storageType: SongStorageType) -> Unit,
) {
    DetailsScreenComponent(
        modifier = modifier,
        items = albumDetails.storedSongs,
        itemContent = { storedSong ->
            HorizontalSongItemComponent(
                modifier = Modifier.fillMaxWidth(),
                song = storedSong.song,
                songDurationText = formatSongDuration(storedSong.song.durationMillis),
                songSizeText = formatSongSize(storedSong.song.songSize),
                songImageUrl = storedSong.song.imageUrl,
                action = {
                    OverflowActionComponent(
                        formatSongStorageType = formatSongStorage,
                        savedSongStorageTypes = storedSong.songStorageTypes,
                        availableSongStorageTypes = availableSongStorageTypes,
                        onSongStorageSelected = onSongStorageSelected,
                        songId = storedSong.song.id,
                    )
                },
            )
        },
        headerTitle = albumDetails.name,
        images = albumDetails.albumPreviewImages,
    )
}

@Composable
private fun OverflowActionComponent(
    modifier: Modifier = Modifier,
    availableSongStorageTypes: List<SongStorageType> = listOf(),
    savedSongStorageTypes: List<SongStorageType> = listOf(),
    formatSongStorageType: (storageType: SongStorageType) -> String,
    onSongStorageSelected: (songId: String, storageType: SongStorageType) -> Unit,
    songId: String,
) {
    Box(modifier = modifier) {
        val isExpanded = remember {
            mutableStateOf(false)
        }

        OverflowIconButtonComponent(
            onClick = {
                isExpanded.value = !isExpanded.value
            }
        )
        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
        ) {
            availableSongStorageTypes.forEach { songStorageType ->
                DropdownMenuItem(
                    text = { TitleSmallTextComponent(text = formatSongStorageType(songStorageType)) },
                    onClick = { onSongStorageSelected(songId, songStorageType) },
                    trailingIcon = {
                        SaveStorageIconButtonComponent(
                            onSaveClick = { onSongStorageSelected(songId, songStorageType) },
                            storageType = songStorageType, isSaved = savedSongStorageTypes.contains(songStorageType),
                        )
                    }
                )
            }
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
                storedSongs = SongMock.generateSongs(40).map { StoredSong(it, allSongStorageTypes()) },
            ),
            availableSongStorageTypes = allSongStorageTypes(),
            onSongStorageSelected = { _, _ -> },
            formatSongSize = { "25.4 MB" },
            formatSongStorage = { "Memory" },
            formatSongDuration = { "24m 2s" },
        )
    }
}