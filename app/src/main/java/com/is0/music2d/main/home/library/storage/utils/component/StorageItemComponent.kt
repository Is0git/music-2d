@file:OptIn(ExperimentalMaterial3Api::class)

package com.is0.music2d.main.home.library.storage.utils.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.duration.DurationComponent
import com.is0.music2d.utils.composable.icon.NextIconComponent
import com.is0.music2d.utils.composable.padding.HorizontalSpacerComponent
import com.is0.music2d.utils.composable.text.LabelMediumTextComponent

@Composable
fun StorageItemComponent(
    modifier: Modifier = Modifier,
    title: String = "",
    durationText: String = "",
    onClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        onClick = onClick,
    ) {
        Row(
            modifier = modifier.padding(AppTheme.dimensions.containerMargin),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            StorageItemTitleComponent(title = title)
            HorizontalSpacerComponent(width = AppTheme.dimensions.mediumComponentGap)
            StorageItemEndComponent(durationText = durationText)
        }
    }
}

@Composable
private fun StorageItemTitleComponent(
    modifier: Modifier = Modifier,
    title: String,
) {
    LabelMediumTextComponent(
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
    CompositionLocalProvider(LocalContentColor provides AppTheme.colors.onSurfaceColorVariant) {
        DurationComponent(
            modifier = modifier,
            durationText = durationText,
            gap = 2.dp,
            textColor = AppTheme.colors.onSurfaceColorVariant,
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
        )
    }
}