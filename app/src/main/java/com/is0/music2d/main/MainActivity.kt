package com.is0.music2d.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.is0.music2d.main.home.HomeScreen
import com.is0.music2d.main.home.my_music.category.CategorizedSongsScreen
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.error.ErrorFormatterProvider
import com.is0.music2d.utils.error.formatter.GeneralErrorFormatter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var generalErrorFormatter: GeneralErrorFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Music2dApp()
        }
    }

    @Composable
    private fun Music2dApp() {
        ErrorFormatterProvider(errorFormatter = generalErrorFormatter) {
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