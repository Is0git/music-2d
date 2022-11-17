package com.is0.music2d.theme.color

import androidx.compose.ui.graphics.Color

data class AppColors(
    val primaryColor: Color = Color.Unspecified,
    val onPrimaryColor: Color = Color.Unspecified,
    val onPrimaryContainerColor: Color = Color.Unspecified,
    val secondaryColor: Color = Color.Unspecified,
    val backgroundColor: Color = Color.Unspecified,
    val onBackgroundColor: Color = Color.Unspecified,
    val onBackgroundColorVariant: Color = Color.Unspecified,
    val surfaceColor: Color = Color.Unspecified,
    val onSurfaceColor: Color = Color.Unspecified,
    val imageScrimColor: Color = Color.Unspecified,
)

fun defaultAppColors(): AppColors = AppColors(
    primaryColor = AppColor.redFish,
    onPrimaryColor = AppColor.white,
    onPrimaryContainerColor = AppColor.white,
    backgroundColor = AppColor.satinDeepBlack,
    onBackgroundColor = AppColor.white,
    onBackgroundColorVariant = AppColor.white,
    surfaceColor = AppColor.shikon,
    onSurfaceColor = AppColor.white,
    imageScrimColor = AppColor.satinDeepBlack,
    secondaryColor = AppColor.sapphireGlitter,
)