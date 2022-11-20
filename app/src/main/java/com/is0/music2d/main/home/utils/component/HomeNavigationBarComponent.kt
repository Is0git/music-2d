package com.is0.music2d.main.home.utils.component

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.is0.music2d.theme.AppTheme

private val BottomNavigationShape = RoundedCornerShape(24.dp)

@Composable
fun HomeNavigationBarComponent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .clip(BottomNavigationShape)
            .border(
                width = 1.dp,
                color = AppTheme.colors.onSurfaceColor.copy(0.1f),
                shape = BottomNavigationShape,
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .then(
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.S) {
                        Modifier.blur(radius = 30.dp)
                    } else {
                        Modifier.alpha(
                            0.6f
                        )
                    }
                )
                .background(Color.Black)
        )

        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                modifier = Modifier.background(
                    color = AppTheme.colors.surfaceColor.copy(alpha = 0.9f),
                    shape = CircleShape,
                ),
                onClick =  onClick,
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = AppTheme.colors.primaryColor,
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = "",
                )
            }
        }
    }
}