package com.is0.music2d.music.song.storage.memory.event

import com.is0.music2d.utils.event.Event

sealed class InMemorySongEvent : Event {
    data class SongDeleted(val songId: String) : InMemorySongEvent()
}