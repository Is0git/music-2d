package com.is0.music2d.music.album.utils.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.is0.music2d.music.album.utils.data.database.entity.AlbumWithSongsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsWithSongsDao {
    @Query("SELECT * FROM AlbumEntity")
    fun watchAlbumsWithSongs(): Flow<List<AlbumWithSongsEntity>>
}