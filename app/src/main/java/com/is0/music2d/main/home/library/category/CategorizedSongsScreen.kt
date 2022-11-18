package com.is0.music2d.main.home.library.category

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.is0.music2d.R
import com.is0.music2d.main.home.library.category.utils.component.CategorySongItemComponent
import com.is0.music2d.main.home.library.category.utils.data.presentation.SongCategoryMock
import com.is0.music2d.main.home.library.category.utils.data.presentation.SongsCategory
import com.is0.music2d.main.home.utils.OnViewAllClick
import com.is0.music2d.music.song.utils.data.domain.Song
import com.is0.music2d.music.song.utils.data.domain.SongSize
import com.is0.music2d.music.song.utils.data.domain.toSize
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.error.handleSnackbarError
import com.is0.music2d.utils.composable.local.LocalDurationFormatter
import com.is0.music2d.utils.composable.local.LocalSizeFormatter
import com.is0.music2d.utils.composable.padding.HorizontalSpacerComponent
import com.is0.music2d.utils.composable.padding.VerticalSpacerComponent
import com.is0.music2d.utils.composable.progress.ProgressComponent
import com.is0.music2d.utils.composable.text.HeadlineMediumTextComponent
import com.is0.music2d.utils.composable.text.LabelMediumTextComponent

@Composable
fun CategorizedSongsScreen(
    modifier: Modifier = Modifier,
    categorizedSongsViewModel: CategorizedSongsViewModel = hiltViewModel(),
    onViewAllClick: OnViewAllClick = {},
) {
    val songDurationFormatter = LocalDurationFormatter.current
    val songSizeFormatter = LocalSizeFormatter.current

    val songsCategories by categorizedSongsViewModel.songsCategories.observeAsState(emptyList())
    val isLoading by categorizedSongsViewModel.isLoading.observeAsState(false)

    categorizedSongsViewModel.error.handleSnackbarError()

    CategorizedSongsContentComponent(
        modifier = modifier,
        songsCategories = songsCategories,
        onSongSizeFormat = { songSize -> songSizeFormatter.formatSize(songSize.toSize()) },
        onSongDurationFormat = songDurationFormatter::formatDuration,
        onViewAllClick = onViewAllClick,
        isLoading = isLoading,
    )
}

@Composable
private fun CategorizedSongsContentComponent(
    modifier: Modifier = Modifier,
    songsCategories: List<SongsCategory> = emptyList(),
    onSongSizeFormat: (songSize: SongSize) -> String,
    onSongDurationFormat: (durationMillis: Long) -> String,
    onViewAllClick: OnViewAllClick = {},
    isLoading: Boolean,
) {
    Box(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 32.dp),
        ) {
            items(songsCategories) { songsCategory ->
                if (songsCategory.songs.isNotEmpty()) {
                    SongCategoryItemComponent(
                        songsCategory = songsCategory,
                        onSongDurationFormat = onSongDurationFormat,
                        onSongSizeFormat = onSongSizeFormat,
                        onViewAllClick = onViewAllClick,
                    )
                }
            }
        }
        if (isLoading) {
            ProgressComponent(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}

@Composable
private fun SongCategoryItemComponent(
    songsCategory: SongsCategory,
    onSongSizeFormat: (songSize: SongSize) -> String,
    onSongDurationFormat: (durationMillis: Long) -> String,
    onViewAllClick: OnViewAllClick = {},
) {
    Column(verticalArrangement = Arrangement.spacedBy(AppTheme.dimensions.categoryTitleGap)) {
        CategoryRowComponent(
            songsCategory = songsCategory,
            onViewAllClick = onViewAllClick,
        )
        CategorySongsListComponent(
            songs = songsCategory.songs,
            onSongSizeFormat = onSongSizeFormat,
            onSongDurationFormat = onSongDurationFormat,
        )
        VerticalSpacerComponent(height = 16.dp)
    }
}

@Composable
private fun CategoryRowComponent(
    modifier: Modifier = Modifier,
    songsCategory: SongsCategory,
    onViewAllClick: OnViewAllClick = {},
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CategoryTitleComponent(
            modifier = Modifier.weight(1f),
            titleText = songsCategory.name
        )
        ViewAllButtonComponent(
            onViewAllClick = onViewAllClick,
            songsCategory = songsCategory,
        )
    }
}

@Composable
private fun CategoryTitleComponent(
    modifier: Modifier = Modifier,
    titleText: String,
) {
    HeadlineMediumTextComponent(
        modifier = modifier.padding(horizontal = AppTheme.dimensions.bodyMargin),
        color = AppTheme.colors.onBackgroundColor,
        text = titleText,
    )
}

@Composable
private fun ViewAllButtonComponent(
    modifier: Modifier = Modifier,
    onViewAllClick: OnViewAllClick,
    songsCategory: SongsCategory
) {
    TextButton(
        modifier = modifier,
        onClick = { onViewAllClick(songsCategory.id) },
    ) {
        LabelMediumTextComponent(
            text = stringResource(R.string.categorized_songs_view_all_button_label),
        )
    }
}

@Composable
private fun CategorySongsListComponent(
    modifier: Modifier = Modifier,
    songs: List<Song>,
    onSongSizeFormat: (songSize: SongSize) -> String,
    onSongDurationFormat: (durationMillis: Long) -> String,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.mediumComponentGap),
    ) {
        songs.forEachIndexed { index, song ->
            if (index == 0 || index == songs.size - 1) {
                HorizontalSpacerComponent(width = AppTheme.dimensions.bodyMargin)
            }
            CategorySongItemComponent(
                song = song,
                onSongDurationFormat = onSongDurationFormat,
                onSongSizeFormat = onSongSizeFormat,
            )
        }
    }
}

@Composable
@Preview
private fun CategorizedSongsContentComponentPreview() {
    AppTheme {
        CategorizedSongsContentComponent(
            songsCategories = SongCategoryMock.getCategories(),
            onSongDurationFormat = { "2m 2s" },
            onSongSizeFormat = { "5mb" },
            isLoading = false,
        )
    }
}