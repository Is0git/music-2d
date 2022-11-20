package com.is0.music2d.main.home.details.utils.component

import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.util.lerp
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.text.HeadlineLargeTextComponent
import kotlin.random.Random

private const val PrimaryAnimatedTitleTargetValue = 400f
private val PrimaryAnimatedTitleDurationRange = 24000..24000

private const val SecondaryAnimatedTitleTargetValue = 800f
private const val SecondaryAnimatedTitleCount = 4
private val SecondaryTitleTranslationXRange = -400f..400f
private val SecondaryAnimatedTitleDurationRange = 6000..24000

private val VerticalBiasInRange = -100..100

@Composable
fun AnimatedDetailsTitleComponent(
    modifier: Modifier = Modifier,
    title: String,
) {
    Box(
        modifier = modifier.fillMaxSize(),
    ) {
        PrimaryAnimatedTitleComponent(title)
        SecondaryAnimatedTitleComponent(title)
    }
}

@Composable
private fun BoxScope.PrimaryAnimatedTitleComponent(title: String) {
    val titleWords = remember(title) {
        title.split(" ")
            .chunked(2)
            .map { wordsChunked -> wordsChunked.joinToString(" ") }
    }

    titleWords.forEachIndexed { index, word ->
        val translationX by animateInfiniteTranslationFloat(
            initialValue = 0f,
            targetValue = PrimaryAnimatedTitleTargetValue,
            durationRange = PrimaryAnimatedTitleDurationRange,
            index = index,
            easing = LinearEasing,
        )

        SongsDetailsTitleComponent(
            modifier = Modifier.scale(4f),
            title = word.uppercase(),
            alignment = if (index % 2 == 0) Alignment.TopCenter else Alignment.BottomCenter,
            translationX = translationX,
        )
    }
}

@Composable
private fun BoxScope.SecondaryAnimatedTitleComponent(title: String) {
    val verticalBiases: List<Float> = (VerticalBiasInRange step (200 / SecondaryAnimatedTitleCount))
        .map { verticalBias -> verticalBias / VerticalBiasInRange.last.toFloat() }

    val translationsX = remember(verticalBiases) {
        verticalBiases.map { bias ->
            lerp(
                start = SecondaryTitleTranslationXRange.start,
                stop = SecondaryTitleTranslationXRange.endInclusive,
                fraction = bias,
            )
        }
    }

    repeat(SecondaryAnimatedTitleCount) { repeatIndex ->
        val translationXInfiniteAnimation by animateInfiniteTranslationFloat(
            index = repeatIndex,
            initialValue = Random(SecondaryTitleTranslationXRange.endInclusive.toInt()).nextFloat(),
            targetValue = SecondaryAnimatedTitleTargetValue,
            durationRange = SecondaryAnimatedTitleDurationRange,
            easing = EaseInOutBack,
        )

        SongsDetailsTitleComponent(
            alignment = BiasAlignment(0f, verticalBiases[repeatIndex]),
            title = title,
            translationX = translationsX[repeatIndex] + translationXInfiniteAnimation,
        )

    }
}

@Composable
private fun animateInfiniteTranslationFloat(
    index: Int,
    initialValue: Float = 0f,
    targetValue: Float,
    durationRange: IntRange,
    easing: Easing,
): State<Float> {
    val transition = rememberInfiniteTransition()

    return transition.animateFloat(
        initialValue = initialValue,
        targetValue = if (index % 2 == 0) targetValue else -targetValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationRange.getRandomDuration(),
                easing = easing,
            ),
            repeatMode = RepeatMode.Reverse,
        )
    )
}

@Composable
private fun IntRange.getRandomDuration() =
    Math.random().toInt() * (first - last) + first

@Composable
private fun BoxScope.SongsDetailsTitleComponent(
    modifier: Modifier = Modifier,
    alignment: Alignment,
    translationX: Float,
    title: String
) {
    HeadlineLargeTextComponent(
        modifier = modifier
            .padding(AppTheme.dimensions.bodyMargin)
            .align(alignment)
            .graphicsLayer {
                this.translationX = translationX
            },
        text = title.uppercase(),
        color = AppTheme.colors.onSurfaceColor.copy(0.1f),
        textAlign = TextAlign.Center,
    )
}