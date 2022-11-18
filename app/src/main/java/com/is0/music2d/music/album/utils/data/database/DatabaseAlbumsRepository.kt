package com.is0.music2d.music.album.utils.data.database

import androidx.room.withTransaction
import com.is0.music2d.music.album.utils.data.AlbumsRepository
import com.is0.music2d.music.album.utils.data.database.entity.AlbumWithSongsEntity
import com.is0.music2d.music.album.utils.data.database.entity.AlbumsWithSongsDao
import com.is0.music2d.music.album.utils.data.database.entity.toAlbum
import com.is0.music2d.music.album.utils.data.database.entity.toEntity
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.storage.database.data.SongsDao
import com.is0.music2d.music.song.storage.memory.repository.entity.toEntity
import com.is0.music2d.utils.database.AppDatabase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseAlbumsRepository @Inject constructor(
    private val albumsWithSongsDao: AlbumsWithSongsDao,
    private val albumsDao: AlbumsDao,
    private val songsDao: SongsDao,
    private val appDatabase: AppDatabase,
) : AlbumsRepository {
    override suspend fun watchAlbums(count: Int): Flow<List<Album>> =
        albumsWithSongsDao.watchAlbumsWithSongs().map { albumsWithSongs ->
            albumsWithSongs.map { album ->
                val filteredSongs = album.songs.filter { song -> song.isSaved }
                album.copy(songs = if (count > 0) filteredSongs.take(count) else filteredSongs)
            }
        }.map { albumWithSongs ->
            albumWithSongs.map(AlbumWithSongsEntity::toAlbum)
        }

    override suspend fun addAlbums(albums: List<Album>) {
        appDatabase.withTransaction {
            albums.forEach { album ->
                albumsDao.addAlbum(album.toEntity())
                songsDao.addSongs(album.songs.map { song -> song.toEntity(album.id) })
            }
        }
    }
}