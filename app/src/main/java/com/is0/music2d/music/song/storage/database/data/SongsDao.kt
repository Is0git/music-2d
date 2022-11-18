package com.is0.music2d.music.song.storage.database.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.is0.music2d.music.song.storage.memory.repository.entity.SongEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SongsDao {
    @Query("SELECT * FROM SongEntity")
    fun watchSongs(): Flow<List<SongEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSongs(songs: List<SongEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSong(song: SongEntity)

    @Query("SELECT * FROM SongEntity")
    suspend fun getSongs(): List<SongEntity>

    @Query("DELETE FROM SongEntity WHERE songId == :songId")
    suspend fun removeSong(songId: String)

    @Query("SELECT SUM(durationMillis) FROM SongEntity WHERE isSaved")
    fun watchTotalSongDuration(): Flow<Long>

    @Query("UPDATE SongEntity SET isSaved = NOT isSaved WHERE songId=:songId")
    suspend fun toggleIsSaved(songId: String)
}