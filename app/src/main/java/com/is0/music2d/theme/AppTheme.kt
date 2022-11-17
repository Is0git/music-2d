package com.is0.music2d.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.is0.music2d.theme.color.AppColors
import com.is0.music2d.theme.color.defaultAppColors
import com.is0.music2d.theme.typograhpy.AppTypography
import com.is0.music2d.theme.typograhpy.defaultAppTypography

private val LocalAppThemeData = compositionLocalOf {
    AppThemeData(
        colors = AppColors(),
        typography = AppTypography(),
        shapes = AppShapes(),
    )
}

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val appThemeData = AppThemeData(
        colors = defaultAppColors(),
        typography = defaultAppTypography(),
        shapes = defaultAppShapes(),
    )
    CompositionLocalProvider(LocalAppThemeData provides appThemeData) {
        content()
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

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = appThemeData.shapes

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = appThemeData.typography
}