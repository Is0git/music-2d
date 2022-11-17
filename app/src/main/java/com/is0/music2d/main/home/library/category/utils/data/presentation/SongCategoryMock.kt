package com.is0.music2d.main.home.library.category.utils.data.presentation

import com.is0.music2d.music.song.utils.data.domain.SongMock
import java.util.UUID

object SongCategoryMock {
    val category: SongsCategory
        get() = SongsCategory(
            id = UUID.randomUUID().toString(),
            name = "Jazz",
            songs = SongMock.generateSongs(),
        )

    fun getCategories(count: Int = 5): List<SongsCategory> = (1..count).map { category }
}