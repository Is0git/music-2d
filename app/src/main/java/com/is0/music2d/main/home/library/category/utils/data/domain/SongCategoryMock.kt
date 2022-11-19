package com.is0.music2d.main.home.library.category.utils.data.domain

import com.is0.music2d.music.song.utils.data.domain.SongMock
import java.util.*

object SongCategoryMock {
    val category: SongsCategory
        get() = SongsCategory(
            id = UUID.randomUUID().toString(),
            name = "Jazz",
            songs = SongMock.generateSongs().map { song -> CategorizedSong(song) },
        )

    fun getCategories(count: Int = 5): List<SongsCategory> = (1..count).map { category }
}