package com.is0.music2d.music.song.storage.utils.merge

import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.data.domain.Song
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SavedSongsMerger @Inject constructor() {
    fun mergeSavedSongs(songs: List<Song>, savedSongs: List<SavedSong>): SongsMergeResult {
        if (savedSongs.isEmpty()) {
            return SongsMergeResult.NotMerged(songs)
        }

        var didMergeSongs = false

        val songsWithStorageType: Map<Song, List<SongStorageType>> =
            songs.associateWith { song ->
                val songsStorageTypes = savedSongs
                    .filter { savedSong -> savedSong.songId == song.id }
                    .map { storedSong -> storedSong.songStorageType }

                if (songsStorageTypes.isNotEmpty()) {
                    didMergeSongs = true
                }
                songsStorageTypes
            }

        if (didMergeSongs) {
            return SongsMergeResult.Merged(songsWithStorageType)
        }

        return SongsMergeResult.NotMerged(songs)
    }
}

sealed class SongsMergeResult {
    data class NotMerged(
        val songs: List<Song>,
    ) : SongsMergeResult()

    data class Merged(
        val songsWithStorageType: Map<Song, List<SongStorageType>>,
    ) : SongsMergeResult()
}