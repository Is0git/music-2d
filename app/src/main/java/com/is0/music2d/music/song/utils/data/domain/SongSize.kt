package com.is0.music2d.music.song.utils.data.domain

import com.is0.music2d.utils.data.size.FileSize
import com.is0.music2d.utils.data.size.SizeUnit

data class SongSize(
    val quantity: Float,
    val unit: SizeUnit,
)

fun SongSize.toSize(): FileSize = FileSize(
    quantity = this.quantity,
    sizeUnit = this.unit,
)