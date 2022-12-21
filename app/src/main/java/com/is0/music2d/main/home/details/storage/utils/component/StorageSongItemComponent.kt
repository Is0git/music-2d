package com.is0.music2d.main.home.details.storage.utils.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.component.HorizontalSongItemComponent
import com.is0.music2d.music.song.utils.data.domain.Song

typealias OnSongSaveClick = (songId: String) -> Unit

@Composable
fun StorageSongItemComponent(
    modifier: Modifier = Modifier,
    detailsSong: StorageDetailsSong,
    songDurationText: String,
    songSizeText: String,
    songImageUrl: String,
    storageType: SongStorageType,
    onSongSaveClick: OnSongSaveClick,
    onSongItemClick: (song: Song) -> Unit = {},
) {
    HorizontalSongItemComponent(
        modifier = modifier,
        song = detailsSong.song,
        songDurationText = songDurationText,
        songSizeText = songSizeText,
        songImageUrl = songImageUrl,
        onSongItemClick = onSongItemClick,
        action = {
            StorageSaveButtonComponent(
                isSaved = detailsSong.isSaved,
                storageType = storageType,
                onSaveClick = { onSongSaveClick(detailsSong.song.id) },
            )
        }
    )
}