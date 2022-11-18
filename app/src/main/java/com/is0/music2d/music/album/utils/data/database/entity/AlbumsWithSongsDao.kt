package com.is0.music2d.music.album.utils.data.database.entity

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsWithSongsDao {
    @Query("SELECT * FROM AlbumEntity")
    fun watchAlbumsWithSongs(): Flow<List<AlbumWithSongsEntity>>
}