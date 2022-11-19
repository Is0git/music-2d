package com.is0.music2d.music.song.storage.filesystem.utils.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.FilesystemSongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilesystemSongsDao {
    fun watchSongs(): Flow<List<FilesystemSongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSong(filesystemSong: FilesystemSongEntity)

    @Delete
    suspend fun removeSong(filesystemSong: FilesystemSongEntity)
}