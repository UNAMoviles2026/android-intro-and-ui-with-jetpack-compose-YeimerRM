package com.moviles.unaroom

import androidx.compose.runtime.Composable
import com.moviles.unaroom.navigation.AppNavHost
import com.moviles.unaroom.ui.theme.UnaRoomTheme

/**
 * Root Composable for the UnaRoom application.
 * Wraps the entire navigation host with the custom theme.
 */
@Composable
fun UnaRoomApp() {
    UnaRoomTheme {
        // Main navigation host for the application
        AppNavHost()
    }
}
