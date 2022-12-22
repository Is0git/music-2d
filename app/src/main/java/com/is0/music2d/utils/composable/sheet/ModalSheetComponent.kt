@file:OptIn(ExperimentalMaterialApi::class)

package com.is0.music2d.utils.composable.sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <T> ModalSheetComponent(
    modifier: Modifier = Modifier,
    sheetContent: @Composable T.() -> Unit = {},
    content: @Composable ModalSheetScope<T>.() -> Unit = {},
) {
    val modalSheetScope = remember { ModalSheetScope<T>() }
    val selectedValue = produceState<T?>(initialValue = null, modalSheetScope.selectedValue.value) {
        value = modalSheetScope.selectedValue.value
    }

    val modalBottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true,
    )

    LaunchedEffect(modalBottomSheetState.isVisible) {
        if (!modalBottomSheetState.isVisible) {
            modalSheetScope.hide()
        }
    }

    LaunchedEffect(selectedValue.value) {
        if (selectedValue.value == null) {
            modalBottomSheetState.hide()
        } else {
            modalBottomSheetState.show()
        }
    }

    ModalBottomSheetLayout(
        modifier = modifier,
        sheetShape = MaterialTheme.shapes.large,
        sheetBackgroundColor = Color.Transparent,
        sheetState = modalBottomSheetState,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.9f)
                    .background(AppTheme.colors.backgroundVariantColor)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 16.dp)
                        .size(width = 48.dp, height = 4.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.onBackgroundColor.copy(0.33f)),
                )
                selectedValue.value?.run { sheetContent(this) }
            }
        },
        content = {
            content(modalSheetScope)
        },
    )
}

@HiltViewModel
class ModalSheetScope<T> internal constructor() {
    private val _selectedValue: MutableStateFlow<T?> = MutableStateFlow(null)
    internal val selectedValue: StateFlow<T?> = _selectedValue

    fun showBottomSheet(selectedValue: T) {
        this._selectedValue.value = selectedValue
    }

    internal fun hide() {
        _selectedValue.value = null
    }
}