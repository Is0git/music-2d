package com.is0.music2d.utils.composable.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.is0.music2d.R

@Composable
fun AppIconComponent(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier
            .size(48.dp)
            .padding(8.dp),
        painter = painterResource(id = R.drawable.app_icon),
        contentDescription = "",
    )
}