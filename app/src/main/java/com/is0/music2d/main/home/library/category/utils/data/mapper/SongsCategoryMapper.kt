package com.is0.music2d.main.home.library.category.utils.data.mapper

import com.is0.music2d.main.home.library.category.utils.data.presentation.SongsCategory
import com.is0.music2d.main.home.library.category.utils.data.presentation.toSongCategory
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.preview.CategorizedSong
import com.is0.music2d.music.song.storage.utils.merge.SongsMergeResult
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SongsCategoryMapper @Inject constructor() {
    fun toSongsCategory(
        songsMergeResult: SongsMergeResult,
        album: Album,
    ): SongsCategory {
        return when (songsMergeResult) {
            is SongsMergeResult.Merged -> {
                SongsCategory(
                    id = album.id,
                    name = album.name,
                    songs = songsMergeResult.songsWithStorageType.map { songStorageTypePair ->
                        CategorizedSong(
                            song = songStorageTypePair.first,
                            songStorageType = songStorageTypePair.second,
                        )
                    }
                )
            }
            else -> album.toSongCategory()
        }
    }
}