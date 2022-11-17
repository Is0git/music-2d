package com.is0.music2d.theme.color

import androidx.compose.ui.graphics.Color

data class AppColors(
    val primaryColor: Color = Color.Unspecified,
    val onPrimaryColor: Color = Color.Unspecified,
    val onBackgroundColor: Color = Color.Unspecified,
)

fun defaultAppColors(): AppColors = AppColors(
    primaryColor = AppColor.rainbowFish,
    onPrimaryColor = AppColor.white,
    onBackgroundColor = AppColor.turbulence,
)