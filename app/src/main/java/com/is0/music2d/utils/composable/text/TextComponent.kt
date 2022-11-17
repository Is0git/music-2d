package com.is0.music2d.utils.composable.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.is0.music2d.theme.AppTheme

@Composable
fun HeadlineTextComponent(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = AppTheme.typography.headline,
        color = color,
    )
}

@Composable
fun TitleLargeTextComponent(
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        textAlign = textAlign,
        style = AppTheme.typography.titleLarge,
    )
}

@Composable
fun TitleSmallTextComponent(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = AppTheme.typography.titleSmall,
    )
}

@Composable
fun LabelLargeTextComponent(
    modifier: Modifier = Modifier,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = AppTheme.typography.labelLarge,
    )
}