package com.is0.music2d.music.song.storage

import android.content.Context
import com.is0.music2d.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

@HiltViewModel
class SongStorageTypeFormatter @Inject constructor(
    @ActivityContext private val context: Context,
) {
    fun formatStorageType(storageType: SongStorageType): String {
        return when (storageType) {
            SongStorageType.MEMORY -> context.getString(R.string.song_storage_type_memory)
            SongStorageType.FILESYSTEM -> context.getString(R.string.song_storage_type_filesystem)
        }
    }
}