package com.is0.music2d.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.is0.music2d.main.home.HomeScreen
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
                HomeScreen()
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