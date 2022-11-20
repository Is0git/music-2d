package com.is0.music2d.music.song.storage.memory.utils.data

import com.is0.music2d.music.song.storage.memory.utils.data.entity.MemorySongEntity
import com.is0.music2d.music.song.storage.memory.utils.data.entity.toDomain
import com.is0.music2d.music.song.storage.memory.utils.data.entity.toMemorySongEntity
import com.is0.music2d.music.song.storage.utils.data.SavedSongsRepository
import com.is0.music2d.music.song.storage.utils.data.domain.SavedSong
import com.is0.music2d.utils.di.qualifier.IO
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MemorySongsRepository @Inject constructor(
    @IO private val dispatcher: CoroutineDispatcher,
) : SavedSongsRepository {
    private val memorySongs: MutableStateFlow<PersistentList<MemorySongEntity>> = MutableStateFlow(persistentListOf())

    override fun watchSavedSongs(): Flow<List<SavedSong>> =
        memorySongs
            .map { songs -> songs.map(MemorySongEntity::toDomain) }
            .flowOn(dispatcher)

    override suspend fun addSavedSong(savedSong: SavedSong) {
        withContext(dispatcher) {
            memorySongs.value.add(savedSong.toMemorySongEntity())
                .distinctBy { song -> song.songId }
                .also { newSongs -> memorySongs.emit(newSongs.toPersistentList()) }
        }
    }

    override suspend fun toggleSavedSong(songId: String) {
        withContext(dispatcher) {
            memorySongs.value.let { songs ->
                val songIndex = songs.indexOfFirst { song -> song.songId == songId }
                if (songIndex == -1) {
                    songs.add(MemorySongEntity(songId))
                } else {
                    songs.removeAt(songIndex)
                }
            }.also { newSongs -> memorySongs.emit(newSongs) }
        }
    }

    override suspend fun watchCount(): Flow<Int> =
        memorySongs.map {
            memorySongs.value.size
        }.flowOn(dispatcher)

    override suspend fun watchSongsByIds(songsIds: List<String>): Flow<List<SavedSong>> =
        memorySongs.map { songs ->
            songs.filter { songs -> songsIds.contains(songs.songId) }
        }
            .map { songs -> songs.map(MemorySongEntity::toDomain) }
            .flowOn(dispatcher)
}