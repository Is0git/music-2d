package com.is0.music2d.music.song.utils.component.local

import androidx.compose.runtime.compositionLocalOf
import com.is0.music2d.music.song.storage.utils.SongStorageTypeFormatter

val LocalSongStorageTypeFormatter = compositionLocalOf<SongStorageTypeFormatter> {
    error("Song storage type formatter has not been provided")
}