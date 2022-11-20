@file:OptIn(ExperimentalAnimationApi::class)

package com.is0.music2d.music.song.storage.utils.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.utils.composable.icon.CheckmarkIconComponent
import com.is0.music2d.utils.composable.icon.StorageIconComponent

@Composable
fun SaveStorageIconButtonComponent(
    modifier: Modifier = Modifier,
    onSaveClick: (isSaved: Boolean) -> Unit,
    storageType: SongStorageType,
    isSaved: Boolean,
) {
    IconButton(
        modifier = modifier,
        onClick = { onSaveClick(isSaved) },
    ) {
        AnimatedContent(targetState = isSaved) { saved ->
            if (saved) {
                CheckmarkIconComponent()
            } else {
                StorageIconComponent(
                    storageType = storageType,
                )
            }
        }
    }
}