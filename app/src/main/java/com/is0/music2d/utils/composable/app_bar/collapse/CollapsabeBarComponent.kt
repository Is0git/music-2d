package com.is0.music2d.utils.composable.app_bar.collapse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import timber.log.Timber
import kotlin.math.absoluteValue

private const val BOTTOM_BAR_THRESH_HOLD = 0.8f

@Composable
fun CollapsableBarComponent(
    modifier: Modifier = Modifier,
    collapsedFraction: Float,
    scrollPosition: Float,
    bottomBar: @Composable CollapsableBarScope.() -> Unit
) {
    Timber.d("Scroll position: $scrollPosition")

    val previousOffset = remember {
        mutableStateOf(0f)
    }
    val isVisibleState = remember {
        mutableStateOf(true)
    }

    val scrollPositionState = remember {
        mutableStateOf(scrollPosition)
    }

    scrollPositionState.value = scrollPosition

    val collapsableBarScope = remember {
        CollapsableBarScope(
            scrollPosition = scrollPositionState,
        )
    }

    LaunchedEffect(collapsedFraction) {
        if ((previousOffset.value - collapsedFraction).absoluteValue >= BOTTOM_BAR_THRESH_HOLD) {
            val isScrollingUp = previousOffset.value >= collapsedFraction
            previousOffset.value = collapsedFraction

            if (isScrollingUp != isVisibleState.value) {
                isVisibleState.value = isScrollingUp
            }
        }
    }

    AnimatedVisibility(
        modifier = modifier
            .padding(horizontal = 64.dp, vertical = 18.dp)
            .requiredHeight(60.dp),
        visible = isVisibleState.value,
        enter = fadeIn() + slideInVertically(initialOffsetY = { it * 2 }),
        exit = fadeOut() + slideOutVertically(targetOffsetY = { it * 2 }),
    ) {
        bottomBar(collapsableBarScope)
    }
}