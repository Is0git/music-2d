package com.is0.music2d.utils.composable.app_bar.collapse

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf

data class CollapsableBarScope(
    internal val scrollPosition: MutableState<Float> = mutableStateOf(0f),
) {
    val indicateScrollUp: State<Boolean>
        get() = derivedStateOf {
            scrollPosition.value > 200f
        }

    fun resetScrollPosition() {
        scrollPosition.value = 0f
    }
}