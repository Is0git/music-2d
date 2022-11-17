package com.is0.music2d.utils.font

import androidx.annotation.FontRes
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

fun createFontFamily(@FontRes fontResId: Int): FontFamily = FontFamily(Font(fontResId))