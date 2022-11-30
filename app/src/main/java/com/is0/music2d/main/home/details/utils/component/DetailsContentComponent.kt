package com.is0.music2d.main.home.details.utils.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.is0.music2d.R
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.text.HeadlineMediumTextComponent

@Composable
fun <T> DetailsScreenComponent(
    modifier: Modifier = Modifier,
    items: List<T>?,
    isLoading: Boolean = false,
    itemContent: @Composable (item: T) -> Unit = {},
) {
    Box(modifier = modifier.fillMaxSize()) {
        LazyColumn {
            items?.forEach {
                item { itemContent(it) }
            }
        }
        if (items.isNullOrEmpty() && !isLoading) {
            HeadlineMediumTextComponent(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(32.dp),
                color = AppTheme.colors.onBackgroundColor,
                textAlign = TextAlign.Center,
                text = stringResource(R.string.storage_details_no_content_message),
            )
        }
    }
}