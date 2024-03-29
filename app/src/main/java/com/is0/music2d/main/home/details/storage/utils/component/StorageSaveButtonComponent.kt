package com.is0.music2d.main.home.details.storage.utils.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.is0.music2d.music.song.storage.utils.composable.SaveStorageIconButtonComponent
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.theme.AppTheme

val isSongSavedKey = SemanticsPropertyKey<Boolean>("isSongSaved")
private var SemanticsPropertyReceiver.isSongSaved by isSongSavedKey

@Composable
fun StorageSaveButtonComponent(
    modifier: Modifier = Modifier,
    onSaveClick: (isSaved: Boolean) -> Unit = {},
    storageType: SongStorageType = SongStorageType.FILESYSTEM,
    isSaved: Boolean = true,
) {
    SaveStorageIconButtonComponent(
        modifier = modifier.semantics {
            isSongSaved = isSaved
        },
        isSaved = isSaved,
        onSaveClick = onSaveClick,
        storageType = storageType,
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