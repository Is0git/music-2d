package com.is0.music2d.music.song.utils.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.is0.music2d.music.song.utils.data.database.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
    @Query("SELECT * FROM SongEntity WHERE songId == :songId")
    fun watchSong(songId: String): Flow<SongEntity>

    @Query("SELECT * FROM SongEntity")
    fun watchSongs(): Flow<List<SongEntity>>

    @Query("SELECT * FROM SongEntity WHERE songId IN (:songIds)")
    suspend fun getSongsByIds(songIds: List<String>): List<SongEntity>

    @Query("DELETE FROM SongEntity WHERE songId == :songId")
    suspend fun removeSong(songId: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSongs(songs: List<SongEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSong(song: SongEntity)
}