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
            return SongsMergeResult.NotMerged
        }

        val mergedSongs = songs.map { song -> getSongsWithStorageType(savedSongs, song) }
        return SongsMergeResult.Merged(mergedSongs)
    }

    private fun getSongsWithStorageType(
        savedSongs: List<SavedSong>,
        song: Song
    ): Pair<Song, SongStorageType> {
        val savedSongIndex = savedSongs.indexOfFirst { savedSong -> song.id == savedSong.songId }

        if (savedSongIndex == -1) {
            return song to SongStorageType.NONE
        }

        return song to savedSongs[savedSongIndex].songStorageType
    }
}

sealed class SongsMergeResult {
    object NotMerged : SongsMergeResult()

    data class Merged(
        val songsWithStorageType: List<Pair<Song, SongStorageType>>,
    ) : SongsMergeResult()
}