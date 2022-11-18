package com.is0.music2d.music.album.usecase

import com.is0.music2d.music.album.utils.data.database.DatabaseAlbumsRepository
import com.is0.music2d.music.album.utils.data.domain.Album
import com.is0.music2d.music.album.utils.data.memory.InMemoryAlbumsRepository
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@ViewModelScoped
class GetUserAlbumsUseCase @Inject constructor(
    private val inMemoryAlbumsRepository: InMemoryAlbumsRepository,
    private val databaseAlbumsRepository: DatabaseAlbumsRepository,
) {
    suspend fun watchUserAlbums(count: Int = -1): Flow<List<Album>> = combine(
        inMemoryAlbumsRepository.watchAlbums(count),
        databaseAlbumsRepository.watchAlbums(count),
    ) { memoryAlbums, databaseAlbums ->
        mergeAlbums(memoryAlbums, databaseAlbums)
    }

    private fun mergeAlbums(firstAlbum: List<Album>, secondAlbum: List<Album>): List<Album> {
        val firstAlbumMap = firstAlbum.associateBy { it.id }
        val secondAlbumMap = secondAlbum.associateBy { it.id }

        val sameAlbums = firstAlbumMap.filter { id -> secondAlbumMap.keys.any { it == id.value.id } }
        val sameAlbumsValues = sameAlbums.values
        val uniqueFirstAlbums = getUniqueAlbums(firstAlbum, sameAlbumsValues)
        val uniqueSecondAlbums = getUniqueAlbums(secondAlbum, sameAlbumsValues)

        val sameAlbumsCombined = sameAlbums.map {
            val firstSameAlbum = firstAlbumMap[it.key]!!
            val secondSameAlbum = firstAlbumMap[it.key]!!

            val newSongs = firstSameAlbum.songs + secondSameAlbum.songs
            firstSameAlbum.copy(songs = newSongs)
        }
        return sameAlbumsCombined + uniqueFirstAlbums + uniqueSecondAlbums
    }

    private fun getUniqueAlbums(
        albums: List<Album>,
        sameAlbums: Collection<Album>
    ) = albums.toMutableList().filter { memoryAlbum -> !sameAlbums.any { it.id == memoryAlbum.id } }
}