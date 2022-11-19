package com.is0.music2d.music.song.storage.utils

import android.content.Context
import com.is0.music2d.R
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SongStorageTypeFormatter @Inject constructor(
    @ActivityContext private val context: Context,
) {
    fun formatStorageType(storageType: SongStorageType): String {
        return when (storageType) {
            SongStorageType.MEMORY -> context.getString(R.string.song_storage_type_memory)
            SongStorageType.FILESYSTEM -> context.getString(R.string.song_storage_type_filesystem)
            SongStorageType.NONE -> ""
        }
    }
}