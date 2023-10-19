package com.erictoader.ui.feature.common.navigator.bottomnav

import androidx.compose.ui.graphics.vector.ImageVector
import com.erictoader.ui.feature.common.navigator.ScreenDestination

data class BottomNavItem(
    val name: String,
    val destination: ScreenDestination,
    val icon: ImageVector,
)
