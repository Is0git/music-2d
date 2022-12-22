@file:OptIn(ExperimentalMaterialApi::class)

package com.is0.music2d.music.playback

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Forward10
import androidx.compose.material.icons.filled.Replay10
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.padding.VerticalSpacerComponent
import com.is0.music2d.utils.composable.text.LabelSmallTextComponent
import timber.log.Timber
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private val SeekBarHeight = 6.dp
private val SeekButtonRadius = 14.dp
private val SeekButtonBorderWidth = 2.dp

@Composable
fun PlaybackControlsComponent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SeekBarComponent(
            startDuration = 137.toDuration(DurationUnit.SECONDS),
            endDuration = 649.toDuration(DurationUnit.SECONDS),
        )
        VerticalSpacerComponent(height = AppTheme.dimensions.mediumComponentGap)
        PlaybackActionsComponent()
    }
}

@Composable
private fun SeekBarComponent(
    modifier: Modifier = Modifier,
    startDuration: Duration,
    endDuration: Duration,
) {
    assert(startDuration <= endDuration) {
        "Start duration can't be greater then end duration"
    }

    val progress: Float = (startDuration / endDuration).toFloat()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        SeekBarHorizontalLineComponent(
            progress = progress,
        )
        SeekBarTimeComponent(
            startTimeText = startDuration.toSeekbarTimeDuration(),
            endTimeText = endDuration.toSeekbarTimeDuration(),
        )
    }
}

@Composable
private fun Duration.toSeekbarTimeDuration(): String {
    try {
        val minutes = inWholeMinutes
        val seconds = inWholeSeconds % 60
        return "$minutes:$seconds"
    } catch (error: Throwable) {
        Timber.e(error)
    }

    return ""
}

@Composable
private fun SeekBarHorizontalLineComponent(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart,
    ) {
        SeekBarLineInactiveComponent()
        SeekBarLineActiveComponent(
            progress = progress,
        )
        SeekButtonComponent(
            modifier = Modifier.align(
                BiasAlignment(
                    horizontalBias = lerp(
                        start = -1f,
                        stop = 1f,
                        fraction = progress,
                    ),
                    verticalBias = 0f,
                ),
            ),
        )
    }
}

@Composable
private fun SeekBarLineActiveComponent(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
) {
    SeekBarLineComponent(
        modifier = modifier.background(
            Brush.horizontalGradient(
                colors = listOf(
                    AppTheme.colors.primaryColor,
                    AppTheme.colors.secondaryColor,
                ),
            ),
        ),
        widthFraction = progress,
    )
}

@Composable
private fun SeekBarLineInactiveComponent(
    modifier: Modifier = Modifier,
) {
    SeekBarLineComponent(
        modifier = modifier.background(
            AppTheme.colors.secondaryColor.copy(0.33f),
        ),
    )
}

@Composable
private fun SeekBarLineComponent(
    modifier: Modifier = Modifier,
    widthFraction: Float = 1f,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(widthFraction)
            .requiredHeight(SeekBarHeight)
            .clip(CircleShape)
            .then(modifier),
    )
}

@Composable
private fun SeekButtonComponent(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(SeekButtonRadius)
            .graphicsLayer {
                translationX = 4f
            }
            .background(
                color = AppTheme.colors.primaryColor,
                shape = CircleShape,
            )
            .border(
                width = SeekButtonBorderWidth,
                color = AppTheme.colors.onPrimaryColor,
                shape = CircleShape,
            ),
    )
}

@Composable
private fun SeekBarTimeComponent(
    modifier: Modifier = Modifier,
    startTimeText: String,
    endTimeText: String,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        SeekBarDurationTextComponent(
            timeText = startTimeText,
        )
        SeekBarDurationTextComponent(
            timeText = endTimeText,
        )
    }
}

@Composable
private fun SeekBarDurationTextComponent(
    modifier: Modifier = Modifier,
    timeText: String,
) {
    LabelSmallTextComponent(
        modifier = modifier,
        text = timeText,
        color = seekBarColor(),
    )
}

@Composable
private fun PlaybackActionsComponent(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.dimensions.mediumComponentGap),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        BackwardActionsComponent(
            modifier = Modifier.weight(1f)
        )
        PlayIconComponent()
        ForwardActionsComponent(
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun BackwardActionsComponent(
    modifier: Modifier = Modifier,
    onSeekClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
) {
    ActionsComponent(
        modifier = modifier,
        firstIcon = Icons.Filled.SkipPrevious,
        onFirstIconClick = onSkipClick,
        secondIcon = Icons.Filled.Replay10,
        onSecondIconClick = onSeekClick,
    )
}

@Composable
private fun ForwardActionsComponent(
    modifier: Modifier = Modifier,
    onSeekClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
) {
    ActionsComponent(
        modifier = modifier,
        firstIcon = Icons.Filled.Forward10,
        onFirstIconClick = onSeekClick,
        secondIcon = Icons.Filled.SkipNext,
        onSecondIconClick = onSkipClick,
    )
}

@Composable
private fun ActionsComponent(
    modifier: Modifier = Modifier,
    firstIcon: ImageVector,
    onFirstIconClick: () -> Unit = {},
    secondIcon: ImageVector,
    onSecondIconClick: () -> Unit = {},
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(
            space = AppTheme.dimensions.smallComponentGap,
            alignment = Alignment.CenterHorizontally,
        ),
    ) {
        ActionIconComponent(
            onActionClick = onFirstIconClick,
            icon = firstIcon,
        )
        ActionIconComponent(
            icon = secondIcon,
            onActionClick = onSecondIconClick,
        )
    }
}

@Composable
private fun ActionIconComponent(
    modifier: Modifier = Modifier,
    onActionClick: () -> Unit = {},
    icon: ImageVector,
) {
    IconButton(
        modifier = modifier,
        onClick = onActionClick,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "",
            tint = seekBarColor(),
        )
    }
}

@Composable
private fun PlayIconComponent(
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit = {},
) {
    Surface(
        modifier = modifier.size(48.dp),
        shape = CircleShape,
        elevation = 4.dp,
        onClick = onPlayClick,
    ) {
        Box(
            modifier = Modifier.background(
                Brush.linearGradient(
                    colors = listOf(
                        AppTheme.colors.secondaryColor,
                        AppTheme.colors.primaryColor,
                    ),
                    start = Offset.Zero,
                    end = Offset.Infinite,
                ),
            ),
        ) {
            Icon(
                modifier = Modifier.align(Alignment.Center),
                imageVector = Icons.Rounded.PlayArrow,
                contentDescription = "",
                tint = AppTheme.colors.onSurfaceColor,
            )
        }
    }
}

@Composable
private fun seekBarColor() = AppTheme.colors.secondaryColor.copy(alpha = 0.42f)

@Composable
@Preview
private fun PlaybackControlsComponentPreview() {
    AppTheme {
        PlaybackControlsComponent()
    }
}