package com.is0.music2d.utils.composable.button

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import com.is0.music2d.theme.AppTheme
import com.is0.music2d.utils.composable.text.LabelLargeTextComponent

@Composable
fun ButtonComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = "",
    containerColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    shape: Shape = ButtonDefaults.shape,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(),
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = shape,
        elevation = elevation,
    ) {
        LabelLargeTextComponent(text = text)
    }
}

@Preview
@Composable
fun ButtonComponentPreview() {
    AppTheme {
        ButtonComponent(text = "Button")
    }
}
