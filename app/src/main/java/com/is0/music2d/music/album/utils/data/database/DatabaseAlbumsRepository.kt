package com.is0.music2d.music.album.utils.data.database

import androidx.room.withTransaction
import com.is0.music2d.music.album.utils.data.AlbumsRepository
import com.is0.music2d.music.album.utils.data.database.dao.AlbumsDao
import com.is0.music2d.music.album.utils.data.database.dao.AlbumsWithSongsDao
import com.is0.music2d.music.album.utils.data.database.entity.AlbumWithSongsEntity
import com.is0.music2d.music.album.utils.data.database.entity.toAlbum
import com.is0.music2d.music.album.utils.data.database.entity.toEntity
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.song.utils.data.database.SongsDao
import com.is0.music2d.music.song.utils.data.database.entity.toEntity
import com.is0.music2d.utils.database.AppDatabase
import com.is0.music2d.utils.di.qualifier.IO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseAlbumsRepository @Inject constructor(
    private val albumsWithSongsDao: AlbumsWithSongsDao,
    private val albumsDao: AlbumsDao,
    private val songsDao: SongsDao,
    private val appDatabase: AppDatabase,
    @IO private val dispatcher: CoroutineDispatcher,
) : AlbumsRepository {
    override suspend fun watchAlbums(count: Int): Flow<List<Album>> =
        albumsWithSongsDao.watchAlbumsWithSongs().map { albumsWithSongs ->
            albumsWithSongs.map { album ->
                if (count > 0) {
                    album.copy(songs = album.songs.take(count))
                } else {
                    album
                }
            }
        }.map { albumWithSongs ->
            albumWithSongs.map(AlbumWithSongsEntity::toAlbum)
        }

    override suspend fun watchAlbum(albumId: String): Flow<Album> =
        withContext(dispatcher) {
            albumsWithSongsDao.watchAlbumWithSongs(albumId).map(AlbumWithSongsEntity::toAlbum)
        }

    override suspend fun addAlbums(albums: List<Album>) {
        appDatabase.withTransaction {
            albums.forEach { album ->
                albumsDao.addAlbum(album.toEntity())
                songsDao.addSongs(album.songs.map { song -> song.toEntity(album.id) })
            }
        }
    }

    override suspend fun exists(): Boolean = appDatabase.albumDao().exists()
}