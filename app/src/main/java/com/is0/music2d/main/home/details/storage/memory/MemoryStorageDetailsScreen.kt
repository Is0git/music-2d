package com.is0.music2d.main.home.details.storage.memory

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.is0.music2d.main.home.details.storage.StorageDetailsScreen

@Composable
fun MemoryStorageDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: MemoryStorageDetailsViewModel,
) {
    StorageDetailsScreen(
        modifier = modifier,
        viewModel = viewModel,
    )
}