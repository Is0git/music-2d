package com.is0.music2d.utils.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.is0.music2d.music.album.utils.data.database.AlbumsDao
import com.is0.music2d.music.album.utils.data.database.entity.AlbumEntity
import com.is0.music2d.music.album.utils.data.database.entity.AlbumsWithSongsDao
import com.is0.music2d.music.song.storage.database.data.SongsDao
import com.is0.music2d.music.song.storage.memory.repository.entity.SongEntity

@Database(entities = [SongEntity::class, AlbumEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songsDao(): SongsDao

    abstract fun albumDao(): AlbumsDao

    abstract fun albumsWithSongsDao(): AlbumsWithSongsDao

    companion object {
        const val DATABASE_NAME = "music2d-database"
    }
}