@file:OptIn(ExperimentalAnimationApi::class)

package com.is0.music2d.main.home.details.storage.utils.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.CheckmarkIconComponent


@Composable
fun StorageSaveButtonComponent(
    modifier: Modifier = Modifier,
    onSaveClick: (isSaved: Boolean) -> Unit = {},
    isSaved: Boolean = true,
) {
    SaveStorageIconButtonComponent(
        modifier = modifier,
        isSaved = isSaved,
        onSaveClick = onSaveClick,
    )
}

@Composable
private fun SaveStorageIconButtonComponent(
    modifier: Modifier = Modifier,
    onSaveClick: (isSaved: Boolean) -> Unit,
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
                StorageIconComponent()
            }
        }
    }
}

@Composable
private fun StorageIconComponent(
    modifier: Modifier = Modifier,
) {
    Icon(
        modifier = modifier,
        imageVector = Icons.Filled.Storage,
        contentDescription = "",
        tint = AppTheme.colors.onSurfaceColor,
    )
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