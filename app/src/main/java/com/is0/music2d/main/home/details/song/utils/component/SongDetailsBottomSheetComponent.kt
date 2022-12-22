@file:OptIn(ExperimentalAnimationApi::class)

package com.is0.music2d.main.home.details.song.utils.component

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.is0.music2d.main.home.details.song.SongDetailsScreen
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.utils.composable.sheet.ModalSheetComponent
import com.is0.music2d.utils.composable.sheet.ModalSheetScope

private const val StartDestination = "songDetails"

@Composable
fun SongDetailsBottomSheetComponent(
    modifier: Modifier = Modifier,
    content: @Composable ModalSheetScope<Song>.() -> Unit = {},
) {
    ModalSheetComponent(
        modifier = modifier,
        sheetContent = { selectedSong ->
            NavHost(
                navController = rememberNavController(),
                startDestination = StartDestination,
            ) {
                composable(StartDestination) {
                    SongDetailsScreen(song = selectedSong)
                }
            }
        },
    ) {
        content(this)
    }
}

fun ModalSheetScope<Song>.showSongDetails(song: Song) {
    showBottomSheet(song)
}