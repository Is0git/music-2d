package com.is0.music2d.utils.size

import com.is0.music2d.utils.data.size.FileSize
import okhttp3.internal.format
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultFileSizeFormatter @Inject constructor() : FileSizeFormatter {
    override fun formatSize(size: FileSize): String = runCatching {
        format(
            FILE_SIZE_FORMAT,
            size.quantity,
            size.sizeUnit.name,
        )
    }.onFailure(Timber::e)
        .getOrNull()
        .orEmpty()

    companion object {
        const val FILE_SIZE_FORMAT = "%.1f %s"
    }
}