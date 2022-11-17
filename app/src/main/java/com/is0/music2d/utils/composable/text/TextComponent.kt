package com.is0.music2d.utils.composable.text

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.is0.music2d.theme.AppTheme

@Composable
fun HeadlineLargeTextComponent(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = AppTheme.typography.headlineLarge,
        color = color,
    )
}

@Composable
fun HeadlineMediumTextComponent(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        style = AppTheme.typography.headlineMedium,
        color = color,
    )
}

@Composable
fun LabelLargeTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    lines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = AppTheme.typography.labelLarge,
        maxLines = lines,
        overflow = overflow,
    )
}

@Composable
fun LabelMediumTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    lines: Int = Int.MAX_VALUE,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = AppTheme.typography.labelMedium,
        maxLines = lines,
        overflow = overflow,
    )
}

@Composable
fun LabelSmallTextComponent(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
        style = AppTheme.typography.labelSmall,
        textAlign = textAlign,
    )
}

@Composable
fun TitleLargeTextComponent(
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    color: Color = Color.Unspecified,
    text: String,
) {
    Text(
        modifier = modifier,
        text = text,
        color = color,
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
