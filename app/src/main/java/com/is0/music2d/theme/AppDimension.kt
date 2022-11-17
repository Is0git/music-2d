package com.is0.music2d.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimension(
    val categoryTitleGap: Dp = Dp.Unspecified,
    val bodyMargin: Dp = Dp.Unspecified,
    val mediumComponentGap: Dp = Dp.Unspecified,
    val smallComponentGap: Dp = Dp.Unspecified,
    val avatarSize: Dp = Dp.Unspecified,
    val smallImageSize: Dp = Dp.Unspecified,
)

@Composable
fun defaultAppDimension() = AppDimension(
    categoryTitleGap = 16.dp,
    bodyMargin = 16.dp,
    mediumComponentGap = 12.dp,
    smallComponentGap = 8.dp,
    avatarSize = 42.dp,
    smallImageSize = 100.dp,
)