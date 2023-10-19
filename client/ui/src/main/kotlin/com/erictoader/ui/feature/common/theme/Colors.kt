package com.erictoader.ui.feature.common.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

fun lightColors(): Colors = darkColors()

fun darkColors(
    primary: Color = Color(0xFF404040),
    primaryVariant: Color = Color(0xFF505050),
    secondary: Color = Color(0xFF03DAC6),
    secondaryVariant: Color = secondary,
    background: Color = Color(0xFF121212),
    surface: Color = Color(0xFF222222),
    error: Color = Color(0xFFCF6679),
    onPrimary: Color = Color.Black,
    onSecondary: Color = Color.Black,
    onBackground: Color = Color(0xFFCCCCCC),
    onSurface: Color = Color(0xFFBBBBBB),
    onError: Color = Color.Black
): Colors = Colors(
    primary,
    primaryVariant,
    secondary,
    secondaryVariant,
    background,
    surface,
    error,
    onPrimary,
    onSecondary,
    onBackground,
    onSurface,
    onError,
    false
)
