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
    ): Pair<Song, List<SongStorageType>> {
        val savedSongsGrouped = savedSongs.groupBy { savedSong -> savedSong.songId }

        if (savedSongsGrouped.isEmpty()) {
            return song to emptyList()
        }

        return song to savedSongsGrouped[song.id]?.map { savedSong -> savedSong.songStorageType }.orEmpty()
    }
}

sealed class SongsMergeResult {
    object NotMerged : SongsMergeResult()

    data class Merged(
        val songsWithStorageType: List<Pair<Song, List<SongStorageType>>>,
    ) : SongsMergeResult()
}