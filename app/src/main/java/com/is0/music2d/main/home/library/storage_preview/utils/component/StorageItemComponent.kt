@file:OptIn(ExperimentalMaterial3Api::class)

package com.is0.music2d.main.home.library.storage_preview.utils.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.is0.music2d.music.song.storage.SongStorageType
import com.is0.music2d.music.song.utils.component.icon.SongDurationIconComponent
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.NextIconComponent
import com.is0.music2d.utils.composable.icon.StorageIconComponent
import com.is0.music2d.utils.composable.layout.LabelLayoutComponent
import com.is0.music2d.utils.composable.padding.HorizontalSpacerComponent
import com.is0.music2d.utils.composable.text.LabelLargeTextComponent
import com.is0.music2d.utils.composable.text.TitleLargeTextComponent

@Composable
fun StorageItemComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    durationText: String = "",
    storageType: SongStorageType,
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            modifier = modifier.padding(AppTheme.dimensions.bodyMargin),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            StorageItemStartComponent(
                title = title,
                storageType = storageType,
            )
            HorizontalSpacerComponent(width = AppTheme.dimensions.mediumComponentGap)
            StorageItemEndComponent(durationText = durationText)
        }
    }
}

@Composable
private fun StorageItemStartComponent(
    modifier: Modifier = Modifier,
    title: String,
    storageType: SongStorageType,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.mediumComponentGap),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StorageIconComponent(modifier = Modifier.requiredSize(42.dp), storageType = storageType)
        StorageItemTitleComponent(title = title)
    }
}

@Composable
private fun StorageItemTitleComponent(
    modifier: Modifier = Modifier,
    title: String,
) {
    TitleLargeTextComponent(
        modifier = modifier,
        text = title,
        color = AppTheme.colors.onSurfaceColor,
    )
}

@Composable
private fun StorageItemEndComponent(
    modifier: Modifier = Modifier,
    durationText: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        StorageItemDurationComponent(
            durationText = durationText,
        )
        NextIconComponent(
            color = AppTheme.colors.onSurfaceColor,
        )
    }
}

@Composable
private fun StorageItemDurationComponent(
    modifier: Modifier = Modifier,
    durationText: String,
) {
    val contentColor = AppTheme.colors.onSurfaceColorVariant
    CompositionLocalProvider(LocalContentColor provides contentColor) {
        LabelLayoutComponent(
            modifier = modifier,
            text = {
                LabelLargeTextComponent(
                    modifier = modifier,
                    color = contentColor,
                    text = durationText,
                )
            },
            icon = {
                SongDurationIconComponent(
                    modifier = modifier.requiredSize(32.dp),
                )
            },
        )
    }
}

@Composable
@Preview
private fun StorageItemComponentPreview() {
    AppTheme {
        StorageItemComponent(
            title = "Memory",
            durationText = "24h 32m 24s",
            storageType = SongStorageType.FILESYSTEM,
        )
    }
}