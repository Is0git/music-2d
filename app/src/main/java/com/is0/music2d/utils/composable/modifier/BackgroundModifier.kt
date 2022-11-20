package com.is0.music2d.utils.composable.modifier

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme

@Composable
fun Modifier.transparentBackground(): Modifier =
    padding(8.dp)
        .clip(RoundedCornerShape(24.dp))
        .background(AppTheme.colors.surfaceColor.copy(alpha = 0.40f))
        .padding(12.dp)