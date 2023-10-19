package com.erictoader.ui.feature.common.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.Dp
import com.erictoader.ui.feature.common.theme.spacing

@Composable
fun GhostText(
    height: Dp = MaterialTheme.spacing.medium,
    width: Dp = MaterialTheme.spacing.XXL
) {
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(50))
            .shimmerEffect()
    )
}
