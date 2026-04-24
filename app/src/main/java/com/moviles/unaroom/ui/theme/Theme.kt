package com.moviles.unaroom.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

/**
 * Custom light color scheme for the application, mapping brand colors 
 * to Material Theme roles.
 */
private val LightColorScheme = lightColorScheme(
    primary = AppPrimary,
    onPrimary = AppBackground,
    background = AppBackground,
    onBackground = AppPrimary,
    surface = AppSurface,
    onSurface = AppPrimary,
    surfaceVariant = AppSurfaceVariant,
    onSurfaceVariant = AppSecondaryText,
    outline = AppBorder,
    error = AppError
)

/**
 * Main application theme wrapper. 
 * Provides colors and typography to its children.
 */
@Composable
fun UnaRoomTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
