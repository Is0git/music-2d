package com.is0.music2d.main.home.details.storage.utils.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.main.home.details.storage.utils.data.StorageDetailsSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.component.HorizontalSongItemComponent

typealias OnSongSaveClick = (songId: String, isSaved: Boolean) -> Unit

@Composable
fun StorageSongItemComponent(
    modifier: Modifier = Modifier,
    detailsSong: StorageDetailsSong,
    songDurationText: String,
    songSizeText: String,
    songImageUrl: String,
    storageType: SongStorageType,
    onSongSaveClick: OnSongSaveClick,
) {
    HorizontalSongItemComponent(
        modifier = modifier,
        song = detailsSong.song,
        songDurationText = songDurationText,
        songSizeText = songSizeText,
        songImageUrl = songImageUrl,
        action = {
            StorageSaveButtonComponent(
                isSaved = detailsSong.isSaved,
                storageType = storageType,
                onSaveClick = { isSaved -> onSongSaveClick(detailsSong.song.id, isSaved) },
            )
        }
    )
}