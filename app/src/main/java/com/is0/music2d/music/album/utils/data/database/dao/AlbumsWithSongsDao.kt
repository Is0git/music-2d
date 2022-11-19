package com.is0.music2d.music.album.utils.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.is0.music2d.music.album.utils.data.database.entity.AlbumWithSongsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsWithSongsDao {
    @Transaction
    @Query("SELECT * FROM AlbumEntity")
    fun watchAlbumsWithSongs(): Flow<List<AlbumWithSongsEntity>>

    @Transaction
    @Query("SELECT * FROM AlbumEntity WHERE albumId == :albumId ")
    fun watchAlbumWithSongs(albumId: String): Flow<AlbumWithSongsEntity>
}