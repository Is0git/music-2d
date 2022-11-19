@file:OptIn(ExperimentalAnimationApi::class)

package com.is0.music2d.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.is0.music2d.main.home.HomeScreen
import com.is0.music2d.main.home.details.album.AlbumDetailsScreen
import com.is0.music2d.main.home.details.storage.StorageDetailsScreen
import com.is0.music2d.main.home.details.storage.filesystem.FileSystemStorageDetailsViewModel
import com.is0.music2d.main.home.details.storage.memory.MemoryStorageDetailsViewModel
import com.is0.music2d.music.song.storage.utils.data.domain.SongStorageType
import com.is0.music2d.music.song.utils.formatter.SongDurationFormatter
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.provider.AppProviders
import com.is0.music2d.utils.error.formatter.GeneralErrorFormatter
import com.is0.music2d.utils.size.DefaultFileSizeFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var generalErrorFormatter: GeneralErrorFormatter

    @Inject
    lateinit var sizeFormatter: DefaultFileSizeFormatter

    @Inject
    lateinit var durationFormatter: SongDurationFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Music2dApp()
        }
    }

    @Composable
    private fun Music2dApp() {
        AppProviders(
            errorFormatter = generalErrorFormatter,
            durationFormatter = durationFormatter,
            sizeFormatter = sizeFormatter,
        ) {
            AppTheme {
                MainNavHost()
            }
        }
    }

    @Composable
    private fun MainNavHost() {
        val navController = rememberAnimatedNavController()

        AnimatedNavHost(
            navController = navController,
            startDestination = MainGraph.Home.routeName,
        ) {
            homeScreen(navController)
            albumDetails(navController)
            storageDetails(navController)
        }
    }

    private fun NavGraphBuilder.albumDetails(navController: NavHostController) {
        composable(
            route = MainGraph.AlbumDetails.routeName,
            arguments = listOf(
                navArgument(MainGraph.AlbumDetails.ALBUM_ID) {
                    type = NavType.StringType
                }
            ),
        ) {
            AlbumDetailsScreen(
                navController = navController,
            )
        }
    }

    private fun NavGraphBuilder.homeScreen(navController: NavHostController) {
        composable(route = MainGraph.Home.routeName) {
            HomeScreen(
                navController = navController,
            )
        }
    }

    private fun NavGraphBuilder.storageDetails(navController: NavHostController) {
        composable(
            route = MainGraph.StorageDetails.routeName,
            arguments = listOf(
                navArgument(MainGraph.StorageDetails.STORAGE_TYPE) {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry: NavBackStackEntry ->
            val storageTypeString = navBackStackEntry.arguments?.getString(MainGraph.StorageDetails.STORAGE_TYPE)
            if (storageTypeString != null) {
                val songStorageType: SongStorageType = SongStorageType.valueOf(storageTypeString)

                StorageDetailsScreen(
                    viewModel = createStorageDetailsViewModel(songStorageType),
                    navController = navController,
                    storageType = songStorageType,
                )
            }
        }
    }

    @Composable
    private fun createStorageDetailsViewModel(songStorageType: SongStorageType) =
        when (songStorageType) {
            SongStorageType.MEMORY -> hiltViewModel<MemoryStorageDetailsViewModel>()
            SongStorageType.FILESYSTEM -> hiltViewModel<FileSystemStorageDetailsViewModel>()
            SongStorageType.NONE -> error("Song storage type not provided")
        }
}