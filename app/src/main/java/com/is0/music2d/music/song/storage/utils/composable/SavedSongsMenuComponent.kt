package com.is0.music2d.music.song.storage.utils.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.utils.composable.icon.OverflowIconButtonComponent
import com.is0.music2d.utils.composable.text.TitleSmallTextComponent

@Composable
fun SavedSongsMenuComponent(
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
fun rememberExpandedState(): MutableState<Boolean> {
    val isExpanded = remember {
        mutableStateOf(false)
    }
    return isExpanded
}