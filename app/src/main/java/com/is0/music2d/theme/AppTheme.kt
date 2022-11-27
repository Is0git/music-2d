package com.is0.music2d.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.is0.music2d.theme.color.AppColors
import com.is0.music2d.theme.color.defaultAppColors
import com.is0.music2d.theme.typograhpy.AppTypography
import com.is0.music2d.theme.typograhpy.defaultAppTypography

private val LocalAppThemeData = compositionLocalOf {
    AppThemeData(
        colors = AppColors(),
        typography = AppTypography(),
        shapes = AppShapes(),
        dimension = AppDimension(),
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val appThemeData = AppThemeData(
        colors = defaultAppColors(),
        typography = defaultAppTypography(),
        shapes = defaultAppShapes(),
        dimension = defaultAppDimension(),
    )

    val systemUiController = rememberSystemUiController()

    systemUiController.setSystemBarsColor(
        color = appThemeData.colors.backgroundColor,
    )

    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = appThemeData.colors.primaryColor,
            surface = appThemeData.colors.surfaceColor,
            onSurface = appThemeData.colors.onSurfaceColor,
        ),
    ) {
        CompositionLocalProvider(LocalAppThemeData provides appThemeData) {
            content()
        }
    }
}

object AppTheme {
    private val appThemeData: AppThemeData
        @Composable
        @ReadOnlyComposable
        get() = LocalAppThemeData.current

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = appThemeData.colors

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = appThemeData.typography

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = appThemeData.shapes

    val dimensions: AppDimension
        @Composable
        @ReadOnlyComposable
        get() = appThemeData.dimension
}