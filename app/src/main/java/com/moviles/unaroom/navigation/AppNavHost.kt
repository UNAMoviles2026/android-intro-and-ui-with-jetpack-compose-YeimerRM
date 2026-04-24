package com.moviles.unaroom.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moviles.unaroom.ui.screens.classrooms.ClassroomsScreen
import com.moviles.unaroom.ui.screens.login.LoginScreen

/**
 * Main Navigation Host for the application.
 * Manages the backstack and transitions between screens.
 */
@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    
    // Global state for success messages that persist across navigations
    var successMessage by rememberSaveable { mutableStateOf<String?>(null) }

    NavHost(
        navController = navController,
        startDestination = AppDestinations.LOGIN,
        modifier = Modifier.fillMaxSize()
    ) {
        // Login Screen Route
        composable(route = AppDestinations.LOGIN) {
            LoginScreen(
                onLoginClick = {
                    successMessage = "Login successful"
                    // Navigate to classrooms and clear the login screen from the backstack
                    navController.navigate(AppDestinations.CLASSROOMS) {
                        popUpTo(AppDestinations.LOGIN) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Classrooms List Screen Route
        composable(route = AppDestinations.CLASSROOMS) {
            ClassroomsScreen(
                successMessage = successMessage,
                onSuccessMessageShown = {
                    successMessage = null
                },
                onLogoutClick = {
                    // Navigate back to login and clear the classrooms screen from the backstack
                    navController.navigate(AppDestinations.LOGIN) {
                        popUpTo(AppDestinations.CLASSROOMS) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
