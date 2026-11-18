package com.vibe.launcher.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val VibeColorScheme = darkColorScheme(
    primary = VibePurple,
    secondary = VibeCyan,
    tertiary = VibePink,
    background = VibeBlack,
    surface = VibeSurface,
    surfaceVariant = VibeSurfaceVariant,
    onPrimary = VibeBlack,
    onSecondary = VibeBlack,
    onBackground = VibeTextPrimary,
    onSurface = VibeTextPrimary,
    onSurfaceVariant = VibeTextSecondary
)

@Composable
fun VibeLauncherTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = VibeColorScheme,
        typography = VibeTypography,
        content = content
    )
}
