package com.is0.music2d.main.home.library.category.use_case

import com.is0.music2d.main.home.library.category.utils.data.mapper.SongsCategoryMapper
import com.is0.music2d.main.home.library.category.utils.data.presentation.SongsCategory
import com.is0.music2d.main.home.library.category.utils.data.presentation.toSongCategory
import com.is0.music2d.music.album.use_case.WatchUserAlbumsUseCase
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.storage.filesystem.utils.data.FilesystemSongsRepository
import com.is0.music2d.music.song.storage.memory.utils.data.MemorySongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.music.song.storage.utils.merge.SavedSongsMerger
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
class WatchSongsCategoriesUseCase @Inject constructor(
    private val watchUserAlbumsUseCase: WatchUserAlbumsUseCase,
    private val memorySongsRepository: MemorySongsRepository,
    private val filesystemSongsRepository: FilesystemSongsRepository,
    private val savedSongsMerger: SavedSongsMerger,
    private val songsCategoryMapper: SongsCategoryMapper,
) {
    suspend fun watchSongCategories(count: Int): Flow<List<SongsCategory>> =
        combine(
            watchUserAlbumsUseCase.watchUserAlbums(count),
            memorySongsRepository.watchSavedSongs(),
            filesystemSongsRepository.watchSavedSongs()
        ) { userAlbums, memorySavedSongs, filesystemSavedSongs ->
            userAlbums.map { album ->
                val allSavedSongs = memorySavedSongs + filesystemSavedSongs
                mapAlbumToCategory(allSavedSongs, album)
            }
        }

    private fun mapAlbumToCategory(
        allSavedSongs: List<SavedSong>,
        currentAlbum: Album
    ): SongsCategory {
        if (allSavedSongs.isEmpty()) {
            return currentAlbum.toSongCategory()
        }

        val songsMergeResult = savedSongsMerger.mergeSavedSongs(currentAlbum.songs, allSavedSongs)

        return songsCategoryMapper.toSongsCategory(songsMergeResult, currentAlbum)
    }
}