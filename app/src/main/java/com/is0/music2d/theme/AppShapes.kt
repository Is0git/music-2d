package com.is0.music2d.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

private val defaultShape = RoundedCornerShape(0)

data class AppShapes(
    val primaryButtonShape: Shape = defaultShape,
    val textFieldShape: Shape = defaultShape,
    val contentLabelShape: Shape = defaultShape,
    val songCoverShape: Shape = defaultShape,
)

@Composable
fun defaultAppShapes(): AppShapes = AppShapes(
    primaryButtonShape = RoundedCornerShape(10.dp),
    textFieldShape = RoundedCornerShape(10.dp),
    contentLabelShape = RoundedCornerShape(42.dp),
    songCoverShape = RoundedCornerShape(60f),
)