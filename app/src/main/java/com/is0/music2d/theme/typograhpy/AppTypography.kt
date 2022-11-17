package com.is0.music2d.theme.typograhpy

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val headline: TextStyle = TextStyle.Default,
    val titleLarge: TextStyle = TextStyle.Default,
    val titleSmall: TextStyle = TextStyle.Default,
    val labelLarge: TextStyle = TextStyle.Default,
    val bodyLarge: TextStyle = TextStyle.Default,
)

fun defaultAppTypography(): AppTypography = AppTypography(
    headline = TextStyle(
        fontFamily = AppFonts.interRegular,
        fontSize = 36.sp,
        fontWeight = FontWeight.ExtraBold,
        lineHeight = 48.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = AppFonts.interRegular,
        fontSize = 22.sp,
        lineHeight = 28.sp,
    ),
    titleSmall = TextStyle(
        fontFamily = AppFonts.interRegular,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
    ),
    labelLarge = TextStyle(
        fontFamily = AppFonts.interRegular,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = AppFonts.interRegular,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )
)