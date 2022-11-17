package com.is0.music2d.utils.size

import com.is0.music2d.utils.data.size.FileSize

interface FileSizeFormatter {
    fun formatSize(size: FileSize): String
}