@file:OptIn(ExperimentalPagerApi::class)

package com.is0.music2d.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.is0.music2d.R
import com.is0.music2d.main.home.library.category.CategorizedSongsScreen
import com.is0.music2d.main.home.utils.data.SongsContentType
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.icon.AppIconComponent
import com.is0.music2d.utils.composable.scaffold.BaseScaffoldComponent
import com.is0.music2d.utils.composable.text.HeadlineLargeTextComponent
import com.is0.music2d.utils.composable.text.TitleSmallTextComponent

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val selectedContentType by homeViewModel.selectedSongContentType.observeAsState(SongsContentType.ALBUMS)
    val username by homeViewModel.username.observeAsState()
    val pagerState = rememberPagerState()

    LaunchedEffect(selectedContentType) {
        pagerState.scrollToPage(homeViewModel.songContentTypes.indexOf(selectedContentType))
    }

    BaseScaffoldComponent(
        modifier = modifier,
        baseViewModel = homeViewModel,
        title = stringResource(R.string.home_screen_title, username.orEmpty()),
        navigationIcon = { AppIconComponent() },
        isAppBarCollapsable = true,
    ) { padding ->
        SongsLibraryPagerComponent(
            modifier = Modifier.padding(padding),
            pagerState = pagerState,
            contentTypes = homeViewModel.songContentTypes,
            onSongContentTypeSelect = homeViewModel::selectContentType,
            selectedSongContentType = selectedContentType,
        )
    }
}

@Composable
private fun SongsLibraryPagerComponent(
    modifier: Modifier = Modifier,
    contentTypes: List<SongsContentType>,
    pagerState: PagerState = rememberPagerState(),
    selectedSongContentType: SongsContentType,
    onSongContentTypeSelect: (contentType: SongsContentType) -> Unit,
) {
    Column(modifier = modifier) {
        SongContentTypeTabRowComponent(
            songContentTypes = contentTypes,
            pagerState = pagerState,
            selectedSongContentType = selectedSongContentType,
            onSongContentTypeSelect = onSongContentTypeSelect,
        )
        SongsContentTypePagerComponent(
            modifier = Modifier.weight(1f),
            contentTypes = contentTypes,
            pagerState = pagerState,
        )
    }
}

@Composable
private fun SongsContentTypePagerComponent(
    modifier: Modifier = Modifier,
    contentTypes: List<SongsContentType>,
    pagerState: PagerState
) {
    HorizontalPager(
        modifier = modifier,
        count = contentTypes.size,
        state = pagerState,
        userScrollEnabled = false,
    ) { page ->
        val contentType = contentTypes[page]
        if (contentType == SongsContentType.ALBUMS) {
            CategorizedSongsScreen()
        } else {
            HeadlineLargeTextComponent(text = "Hello")
        }
    }
}

@Composable
private fun SongContentTypeTabRowComponent(
    modifier: Modifier = Modifier,
    pagerState: PagerState = rememberPagerState(),
    songContentTypes: List<SongsContentType> = emptyList(),
    selectedSongContentType: SongsContentType,
    onSongContentTypeSelect: (contentType: SongsContentType) -> Unit,
) {
    TabRow(
        modifier = modifier,
        selectedTabIndex = pagerState.currentPage,
    ) {
        songContentTypes.forEach { songContentType ->
            Tab(
                selectedContentColor = AppTheme.colors.secondaryColor,
                unselectedContentColor = AppTheme.colors.onSurfaceColor,
                text = {
                    TitleSmallTextComponent(
                        text = songContentType.name,
                    )
                },
                selected = selectedSongContentType == songContentType,
                onClick = { onSongContentTypeSelect(songContentType) },
            )
        }
    }
}


@Composable
private fun HomeScreenContentComponent(
    modifier: Modifier = Modifier,
) {

}