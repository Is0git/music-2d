package com.is0.music2d.theme.color

import androidx.compose.ui.graphics.Color

data class AppColors(
    val primaryColor: Color = Color.Unspecified,
    val onPrimaryColor: Color = Color.Unspecified,
    val onPrimaryContainerColor: Color = Color.Unspecified,
    val secondaryColor: Color = Color.Unspecified,
    val backgroundColor: Color = Color.Unspecified,
    val backgroundVariantColor: Color = Color.Unspecified,
    val onBackgroundColor: Color = Color.Unspecified,
    val onBackgroundVariantColor: Color = Color.Unspecified,
    val surfaceColor: Color = Color.Unspecified,
    val onSurfaceVariantColor: Color = Color.Unspecified,
    val onSurfaceColor: Color = Color.Unspecified,
    val imageScrimColor: Color = Color.Unspecified,
    val positiveColor: Color = Color.Unspecified,
    val placeholderColor: Color = Color.Unspecified,
)

fun defaultAppColors(): AppColors = AppColors(
    primaryColor = AppColor.redFish,
    onPrimaryColor = AppColor.white,
    onPrimaryContainerColor = AppColor.white,
    backgroundColor = AppColor.shikon,
    backgroundVariantColor = AppColor.nordicNoir,
    onBackgroundColor = AppColor.white,
    onBackgroundVariantColor = AppColor.turbulence,
    surfaceColor = AppColor.shikon,
    onSurfaceColor = AppColor.white,
    onSurfaceVariantColor = AppColor.turbulence,
    imageScrimColor = AppColor.satinDeepBlack,
    secondaryColor = AppColor.sapphireGlitter,
    positiveColor = AppColor.peppermintToad,
    placeholderColor = AppColor.satinDeepBlack.copy(0.3f),
)