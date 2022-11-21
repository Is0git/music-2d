@file:OptIn(ExperimentalCoroutinesApi::class)

package com.is0.music2d.music.song.storage.use_case

import com.is0.music2d.music.album.utils.data.database.DatabaseAlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.domain.StoredSongsAlbum
import com.is0.music2d.music.album.utils.data.domain.mapper.StoredAlbumSongsMapper
import com.is0.music2d.music.song.storage.filesystem.utils.data.FilesystemSongsRepository
import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import com.is0.music2d.music.song.storage.utils.merge.SongsMergeResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@ViewModelScoped
class WatchAlbumWithStoredSongsUseCase @Inject constructor(
    private val databaseAlbumsRepository: DatabaseAlbumsRepository,
    private val memorySongsRepository: MemorySongsRepository,
    private val filesystemSongsRepository: FilesystemSongsRepository,
    private val savedSongsMerger: SavedSongsMerger,
    private val storedAlbumSongsMapper: StoredAlbumSongsMapper,
) {
    fun watchAlbumWithStoredSongs(albumId: String): Flow<StoredSongsAlbum> =
        combine(
            memorySongsRepository.watchCount(),
            filesystemSongsRepository.watchCount()
        ) { memorySongsCount, filesystemSongsCount -> memorySongsCount + filesystemSongsCount }
            .scan(-1) { first, second -> if (first != second) second else first }
            .distinctUntilChanged()
            .flatMapLatest {
                databaseAlbumsRepository.watchAlbum(albumId)
                    .flatMapLatest { album ->
                        val songIds = album.songs.map { it.id }
                        mergeSongs(
                            songIds = songIds,
                            album = album,
                        )
                    }
            }

    private fun mergeSongs(
        songIds: List<String>,
        album: Album
    ) = combine(
        memorySongsRepository.watchSongsByIds(songIds),
        filesystemSongsRepository.watchSongsByIds(songIds),
    ) { memorySavedSongs, filesystemSavedSongs ->
        val allSavedSongs = memorySavedSongs + filesystemSavedSongs
        when (val songsMergeResult = savedSongsMerger.mergeSavedSongs(album.songs, allSavedSongs)) {
            is SongsMergeResult.Merged -> storedAlbumSongsMapper.toStoredSongsAlbumMerged(
                album = album,
                songsWithStorageType = songsMergeResult.songsWithStorageType,
            )
            is SongsMergeResult.NotMerged -> storedAlbumSongsMapper.toStoredAlbumSongs(album)
        }
    }
}