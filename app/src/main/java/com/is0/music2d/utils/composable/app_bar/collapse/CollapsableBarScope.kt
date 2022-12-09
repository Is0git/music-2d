@file:OptIn(ExperimentalMaterial3Api::class)

package com.is0.music2d.utils.composable.app_bar.collapse

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf

data class CollapsableBarScope(
    internal val scrollPosition: MutableState<Float> = mutableStateOf(0f),
    internal val topAppBarState: TopAppBarState,
    val collapseThreshHoldHeight: Float = DEFAULT_COLLAPSE_THRESH_HOLD_HEIGHT,
) {
    val indicateScrollUp: State<Boolean>
        get() = derivedStateOf {
            scrollPosition.value > collapseThreshHoldHeight
        }

    fun resetScrollPosition() {
        scrollPosition.value = 0f
        topAppBarState.contentOffset = 0f
    }

    companion object {
        private const val DEFAULT_COLLAPSE_THRESH_HOLD_HEIGHT = 200f
    }
}