package com.is0.music2d.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.is0.music2d.music.album.utils.data.database.dao.AlbumsDao
import com.is0.music2d.music.album.utils.data.database.dao.AlbumsWithSongsDao
import com.is0.music2d.music.album.utils.data.database.entity.AlbumEntity
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.dao.FilesystemSongsDao
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.FilesystemSongEntity
import com.is0.music2d.music.song.utils.data.database.data.SongsDao
import com.is0.music2d.music.song.utils.data.database.data.entity.SongEntity

@Database(entities = [SongEntity::class, AlbumEntity::class, FilesystemSongEntity::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songsDao(): SongsDao

    abstract fun albumDao(): AlbumsDao

    abstract fun albumsWithSongsDao(): AlbumsWithSongsDao

    abstract fun filesystemSongsDao(): FilesystemSongsDao

    companion object {
        const val DATABASE_NAME = "music2d-database"
    }
}