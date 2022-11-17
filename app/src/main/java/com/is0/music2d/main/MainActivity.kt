@file:OptIn(ExperimentalAnimationApi::class)

package com.is0.music2d.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.is0.music2d.main.home.HomeScreen
import com.is0.music2d.main.home.details.AlbumDetailsScreen
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
            val navController = rememberAnimatedNavController()

            AppTheme {
                AnimatedNavHost(
                    navController = navController,
                    startDestination = MainGraph.Home.routeName,
                ) {
                    composable(MainGraph.Home.routeName) {
                        HomeScreen(
                            navController = navController,
                        )
                    }
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
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AppTheme {
        Greeting("Android")
    }
}