@file:OptIn(ExperimentalMaterialApi::class)

package com.is0.music2d.main.home.details.album

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.is0.music2d.main.home.details.album.data.domain.AlbumDetails
import com.is0.music2d.main.home.details.song.utils.component.SongDetailsBottomSheetComponent
import com.is0.music2d.main.home.details.song.utils.component.showSongDetails
import com.is0.music2d.main.home.details.utils.component.DetailsScreenComponent
import com.is0.music2d.main.home.details.utils.component.SongsDetailsHeaderComponent
import com.is0.music2d.music.song.storage.StoredSongsToggleViewModel
import com.is0.music2d.music.song.storage.utils.composable.SavedSongsMenuComponent
import com.is0.music2d.music.song.storage.utils.composable.StorageProviders
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.music.song.utils.component.HorizontalSongItemComponent
import com.is0.music2d.music.song.utils.component.local.LocalSongStorageTypeFormatter
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.domain.SongMock
import com.is0.music2d.music.song.utils.data.domain.SongSize
import com.is0.music2d.music.song.utils.data.domain.toSize
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent
import com.is0.music2d.utils.composable.scaffold.CollapsableScaffoldComponent

@Composable
fun AlbumDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AlbumDetailsViewModel = hiltViewModel(),
    savedSongsToggleViewModel: StoredSongsToggleViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {
    val albumDetails by viewModel.albumDetails.observeAsState(AlbumDetails.empty())
    val isLoading by viewModel.isLoading.observeAsState(false)

    val songDurationFormatter = LocalDurationFormatter.current
    val songSizeFormatter = LocalSizeFormatter.current

    StorageProviders {
        val songStorageTypeFormatter = LocalSongStorageTypeFormatter.current

        val formatDuration = { duration: Long ->
            songDurationFormatter.formatDuration(
                durationMillis = duration,
                showZero = true,
            )
        }

        SongDetailsBottomSheetComponent(
            modifier = modifier,
        ) {
            CollapsableScaffoldComponent(
                title = albumDetails.name,
                toolBarBackground = {
                    SongsDetailsHeaderComponent(
                        title = albumDetails.name,
                        images = albumDetails.albumPreviewImages,
                        durationText = formatDuration(albumDetails.totalDuration),
                        songCount = albumDetails.albumSongsCount,
                    )
                },
                onNavigateUp = navController::popBackStack,
            ) {
                BaseScaffoldComponent(
                    baseViewModel = viewModel,
                    isLoading = isLoading,
                ) { padding ->
                    AlbumDetailsContentComponent(
                        modifier = Modifier.padding(padding),
                        albumDetails = albumDetails,
                        formatSongSize = { songSize -> songSizeFormatter.formatSize(songSize.toSize()) },
                        formatDuration = formatDuration,
                        formatSongStorage = songStorageTypeFormatter::formatStorageType,
                        availableSongStorageTypes = viewModel.availableSongStorageTypes,
                        onSongStorageSelected = savedSongsToggleViewModel::toggleSavedSong,
                        onSongItemClick = ::showSongDetails,
                    )
                }
            }

        }
    }
}

@Composable
private fun AlbumDetailsContentComponent(
    modifier: Modifier = Modifier,
    albumDetails: AlbumDetails,
    formatDuration: (duration: Long) -> String,
    formatSongSize: (size: SongSize) -> String,
    formatSongStorage: (storageType: SongStorageType) -> String,
    availableSongStorageTypes: List<SongStorageType> = emptyList(),
    onSongStorageSelected: (songId: String, storageType: SongStorageType) -> Unit,
    onSongItemClick: (song: Song) -> Unit = {},
) {
    DetailsScreenComponent(
        modifier = modifier,
        items = albumDetails.storedSongs,
    ) { storedSong ->
        HorizontalSongItemComponent(
            modifier = Modifier.fillMaxWidth(),
            song = storedSong.song,
            songDurationText = formatDuration(storedSong.song.durationMillis),
            songSizeText = formatSongSize(storedSong.song.songSize),
            songImageUrl = storedSong.song.imageUrl,
            onSongItemClick = onSongItemClick,
            action = {
                SavedSongsMenuComponent(
                    formatSongStorageType = formatSongStorage,
                    savedSongStorageTypes = storedSong.songStorageTypes,
                    availableSongStorageTypes = availableSongStorageTypes,
                    onSongStorageSelected = onSongStorageSelected,
                    songId = storedSong.song.id,
                )
            },
        )
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
            formatDuration = { "24m 2s" },
        )
    }
}