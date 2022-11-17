package com.is0.music2d.music.song.storage.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.is0.music2d.music.song.storage.SongStorageTypeFormatter
import com.is0.music2d.music.song.utils.component.local.LocalSongStorageTypeFormatter

@Composable
fun StorageProviders(content: @Composable () -> Unit = {}) {
    val context = LocalContext.current
    CompositionLocalProvider(
        LocalSongStorageTypeFormatter provides remember { SongStorageTypeFormatter(context) },
    ) {
        content()
    }
}