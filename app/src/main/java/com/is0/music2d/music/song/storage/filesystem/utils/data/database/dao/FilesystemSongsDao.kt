package com.is0.music2d.music.song.storage.filesystem.utils.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.is0.music2d.music.song.storage.filesystem.utils.data.database.entity.FilesystemSongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilesystemSongsDao {
    @Query("SELECT * FROM FilesystemSongEntity")
    fun watchSongs(): Flow<List<FilesystemSongEntity>>

    @Query("SELECT * FROM FilesystemSongEntity WHERE songId IN (:songsIds)")
    fun watchSongsByIds(songsIds: List<String>): Flow<List<FilesystemSongEntity>>

    @Query("SELECT EXISTS(SELECT * FROM FilesystemSongEntity WHERE songId = :songId)")
    suspend fun exists(songId: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSong(song: FilesystemSongEntity)

    @Delete
    suspend fun deleteSong(song: FilesystemSongEntity)

    @Query("SELECT COUNT(songId) FROM FilesystemSongEntity")
    fun watchCount(): Flow<Int>
}