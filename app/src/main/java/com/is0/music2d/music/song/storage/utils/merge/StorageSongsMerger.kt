package com.is0.music2d.music.song.storage.utils.merge

import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.utils.data.domain.Song
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SavedSongsMerger @Inject constructor() {
    fun mergeSavedSongs(songs: List<Song>, savedSongs: List<SavedSong>): List<Song> {
        if (savedSongs.isEmpty()) {
            return songs
        }

        return songs.map { song -> updateSongsWithStorageType(savedSongs, song) }
    }

    private fun updateSongsWithStorageType(
        savedSongs: List<SavedSong>,
        song: Song
    ): Song {
        val savedSongIndex = savedSongs.indexOfFirst { savedSong -> song.id == savedSong.songId }

        if (savedSongIndex == -1) {
            return song
        }

        return song.copy(songStorageType = savedSongs[savedSongIndex].songStorageType)
    }
}