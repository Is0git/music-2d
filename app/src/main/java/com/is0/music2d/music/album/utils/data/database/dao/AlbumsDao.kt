package com.is0.music2d.music.album.utils.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.is0.music2d.music.album.utils.data.database.entity.AlbumEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AlbumsDao {
    @Query("SELECT * FROM AlbumEntity")
    fun watchAlbums(): Flow<List<AlbumEntity>>

    @Insert
    suspend fun addAlbums(albums: List<AlbumEntity>)

    @Insert
    suspend fun addAlbum(album: AlbumEntity)


    @Query("SELECT EXISTS(SELECT * FROM AlbumEntity)")
    fun exists(): Boolean
}