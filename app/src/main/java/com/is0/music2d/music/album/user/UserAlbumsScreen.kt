package com.is0.music2d.music.album.user

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent

@Composable
fun UserAlbumsScreen(
    modifier: Modifier = Modifier,
    userAlbumsScreenViewModel: UserAlbumsScreenViewModel = hiltViewModel(),
) {
    BaseScaffoldComponent(
        modifier = modifier,
        baseViewModel = userAlbumsScreenViewModel,
    ) {
        UserAlbumContentComponent(
            modifier = modifier,
        )
    }
}

@Composable
private fun UserAlbumContentComponent(
    modifier: Modifier = Modifier,
) {

}