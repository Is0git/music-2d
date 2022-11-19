package com.is0.music2d.utils.composable.icon

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType

@Composable
fun StorageIconComponent(
    modifier: Modifier = Modifier,
    storageType: SongStorageType,
) {
    when (storageType) {
        SongStorageType.MEMORY -> MemoryIconComponent(modifier)
        SongStorageType.FILESYSTEM -> FilesystemIconComponent(modifier)
        else -> {}
    }
}