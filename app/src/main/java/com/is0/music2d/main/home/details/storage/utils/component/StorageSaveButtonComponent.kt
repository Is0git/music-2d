@file:OptIn(ExperimentalAnimationApi::class)

package com.is0.music2d.main.home.details.storage.utils.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.is0.music2d.music.song.storage.SongStorageType
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.CheckmarkIconComponent
import com.is0.music2d.utils.composable.icon.StorageIconComponent

@Composable
fun StorageSaveButtonComponent(
    modifier: Modifier = Modifier,
    onSaveClick: (isSaved: Boolean) -> Unit = {},
    storageType: SongStorageType = SongStorageType.FILESYSTEM,
    isSaved: Boolean = true,
) {
    SaveStorageIconButtonComponent(
        modifier = modifier,
        isSaved = isSaved,
        onSaveClick = onSaveClick,
        storageType = storageType,
    )
}

@Composable
private fun SaveStorageIconButtonComponent(
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

@Composable
@Preview
fun StorageSaveButtonComponentNotSavedPreview() {
    AppTheme {
        StorageSaveButtonComponent(
            isSaved = false,
        )
    }
}

@Composable
@Preview
fun StorageSaveButtonComponentSavedPreview() {
    AppTheme {
        StorageSaveButtonComponent(
            isSaved = true,
        )
    }
}