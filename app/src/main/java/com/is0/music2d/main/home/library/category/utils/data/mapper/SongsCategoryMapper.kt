package com.is0.music2d.main.home.library.category.utils.data.mapper

import com.is0.music2d.music.song.storage.utils.data.domain.StoredSong
import com.is0.music2d.main.home.library.category.utils.data.domain.SongsCategory
import com.is0.music2d.main.home.library.category.utils.data.domain.toSongCategory
import com.is0.music2d.music.album.utils.data.domain.Album
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
            is SongsMergeResult.Merged -> SongsCategory(
                id = album.id,
                name = album.name,
                songs = songsMergeResult.songsWithStorageType.map { songStorageTypePair ->
                    StoredSong(
                        song = songStorageTypePair.first,
                        songStorageTypes = songStorageTypePair.second,
                    )
                }
            )
            else -> album.toSongCategory()
        }
    }
}