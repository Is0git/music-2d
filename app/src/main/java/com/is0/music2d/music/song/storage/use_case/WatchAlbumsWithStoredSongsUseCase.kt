package com.is0.music2d.music.song.storage.use_case

import com.is0.music2d.music.album.use_case.WatchUserAlbumsUseCase
import com.is0.music2d.music.album.utils.data.domain.StoredSongsAlbum
import com.is0.music2d.music.album.utils.data.domain.mapper.StoredAlbumSongsMapper
import com.is0.music2d.music.song.storage.filesystem.utils.data.FilesystemSongsRepository
import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import com.is0.music2d.music.song.storage.utils.merge.SongsMergeResult
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
class WatchAlbumsWithStoredSongsUseCase @Inject constructor(
    private val watchUserAlbumsUseCase: WatchUserAlbumsUseCase,
    private val memorySongsRepository: MemorySongsRepository,
    private val filesystemSongsRepository: FilesystemSongsRepository,
    private val savedSongsMerger: SavedSongsMerger,
    private val storedAlbumSongsMapper: StoredAlbumSongsMapper,
) {
    fun watchAlbumsWithStoredSongs(count: Int = 1): Flow<List<StoredSongsAlbum>> = combine(
        watchUserAlbumsUseCase.watchUserAlbums(count),
        memorySongsRepository.watchSavedSongs(),
        filesystemSongsRepository.watchSavedSongs(),
    ) { userAlbums, memorySavedSongs, filesystemSavedSongs ->
        userAlbums.map { album ->
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
}