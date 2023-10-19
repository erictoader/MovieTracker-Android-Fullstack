package com.erictoader.ui.feature.common.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

@Composable
fun AppTheme(darkMode: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkMode) darkColors() else lightColors()

    CompositionLocalProvider(
        LocalSpacing provides Spacing(),
        LocalFontSize provides FontSize()
    ) {
        MaterialTheme(
            colors = colors,
            content = content
        )
    }
}
