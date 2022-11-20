package com.is0.music2d.main.home.details.album

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.is0.music2d.main.home.details.utils.component.SongsDetailsHeaderComponent
import com.is0.music2d.music.song.storage.utils.composable.SaveStorageIconButtonComponent
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.music.song.storage.utils.data.domain.allSongStorageTypes
import com.is0.music2d.music.song.utils.component.HorizontalSongItemComponent
import com.is0.music2d.music.song.utils.data.domain.SongMock
import com.is0.music2d.music.song.utils.data.domain.toSize
import com.is0.music2d.music.song.utils.formatter.FormatSongDuration
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.OverflowIconButtonComponent
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent
import com.is0.music2d.utils.composable.text.TitleSmallTextComponent
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
            onSongStorageSelected = viewModel::toggleSavedSong,
        )
    }
}

@Composable
private fun AlbumDetailsContentComponent(
    modifier: Modifier = Modifier,
    albumDetails: AlbumDetails,
    formatSongDuration: FormatSongDuration,
    formatFileSize: FormatFileSize,
    songStorageTypes: List<SongStorageType> = emptyList(),
    formatSongStorageType: (storageType: SongStorageType) -> String = { "" },
    onSongStorageSelected: (songId: String, storageType: SongStorageType) -> Unit,
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
                action = {
                    OverflowActionComponent(
                        formatSongStorageType = formatSongStorageType,
                        savedSongStorageTypes = storedSong.songStorageTypes,
                        allSongStorageTypes = songStorageTypes,
                        onSongStorageSelected = { songStorageType ->
                            onSongStorageSelected(
                                storedSong.song.id,
                                songStorageType,
                            )
                        },
                    )
                },
            )
        }
    }
}

@Composable
private fun OverflowActionComponent(
    modifier: Modifier = Modifier,
    allSongStorageTypes: List<SongStorageType> = listOf(),
    savedSongStorageTypes: List<SongStorageType> = listOf(),
    formatSongStorageType: (storageType: SongStorageType) -> String,
    onSongStorageSelected: (storageType: SongStorageType) -> Unit,
) {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    Box(modifier = modifier) {
        OverflowIconButtonComponent(
            onClick = {
                isExpanded.value = !isExpanded.value
            }
        )
        DropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
        ) {
            allSongStorageTypes.forEach { songStorageType ->
                DropdownMenuItem(
                    text = { TitleSmallTextComponent(text = formatSongStorageType(songStorageType)) },
                    onClick = { onSongStorageSelected(songStorageType) },
                    trailingIcon = {
                        SaveStorageIconButtonComponent(
                            onSaveClick = { onSongStorageSelected(songStorageType) },
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
                storedSong = SongMock.generateSongs(40).map { StoredSong(it, allSongStorageTypes()) },
            ),
            formatSongDuration = { "2h 3m" },
            formatFileSize = { "12MB" },
            songStorageTypes = allSongStorageTypes(),
            formatSongStorageType = { "Storage type example" },
            onSongStorageSelected = { _, _ -> }
        )
    }
}