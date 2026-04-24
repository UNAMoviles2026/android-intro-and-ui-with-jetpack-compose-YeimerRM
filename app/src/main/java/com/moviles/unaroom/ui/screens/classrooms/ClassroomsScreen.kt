package com.moviles.unaroom.ui.screens.classrooms

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.moviles.unaroom.data.Classroom
import com.moviles.unaroom.ui.components.ClassroomCard
import com.moviles.unaroom.ui.theme.*

/**
 * Bottom navigation bar for the classrooms screen.
 */
@Composable
private fun AppBottomBar() {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        // Home Navigation Item
        NavigationBarItem(
            selected = true,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text(text = "Home") },
            colors = navigationBarItemColors()
        )

        // Calendar Navigation Item
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.CalendarToday,
                    contentDescription = "Calendar"
                )
            },
            label = { Text(text = "Calendar") },
            colors = navigationBarItemColors()
        )

        // Profile Navigation Item
        NavigationBarItem(
            selected = false,
            onClick = { },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Profile"
                )
            },
            label = { Text(text = "Profile") },
            colors = navigationBarItemColors()
        )
    }
}

/**
 * Custom colors for the navigation bar items.
 */
@Composable
private fun navigationBarItemColors() = NavigationBarItemDefaults.colors(
    selectedIconColor = AppPrimary,
    selectedTextColor = AppPrimary,
    unselectedIconColor = AppNavUnselected,
    unselectedTextColor = AppNavUnselected,
    indicatorColor = AppBackground
)

/**
 * Static mock data for demo purposes.
 */
private val mockClassrooms = listOf(
    Classroom(name = "Aula A101", capacity = 30, location = "Building 1"),
    Classroom(name = "Aula B205", capacity = 25, location = "Building 2"),
    Classroom(name = "Lecture Hall 101", capacity = 150, location = "Building 3"),
    Classroom(name = "Aula C310", capacity = 24, location = "Building 1"),
    Classroom(name = "Meeting Room 201", capacity = 12, location = "Building 2")
)

/**
 * Main Classrooms Screen component showing a list of available rooms.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClassroomsScreen(
    modifier: Modifier = Modifier,
    classrooms: List<Classroom> = mockClassrooms,
    successMessage: String? = null,
    onSuccessMessageShown: () -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Show success message (e.g., after login) when it's provided
    LaunchedEffect(successMessage) {
        if (successMessage != null) {
            snackbarHostState.showSnackbar(message = successMessage)
            onSuccessMessageShown()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            // Screen header with title and logout action
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Available Classrooms",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                actions = {
                    IconButton(onClick = onLogoutClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Logout,
                            contentDescription = "Logout",
                            tint = AppPrimary
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            // FAB for adding new classrooms
            FloatingActionButton(
                onClick = { },
                containerColor = AppPrimary,
                contentColor = AppBackground
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add classroom"
                )
            }
        },
        bottomBar = {
            AppBottomBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { snackbarData ->
                Snackbar(
                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                    snackbarData = snackbarData,
                    containerColor = AppPrimary,
                    contentColor = AppBackground
                )
            }
        }
    ) { paddingValues ->
        // Content area: Show empty state or list of classrooms
        if (classrooms.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No classrooms available",
                    color = AppSecondaryText,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(
                    start = 14.dp,
                    end = 14.dp,
                    top = 12.dp,
                    bottom = 96.dp // Extra padding for FAB/BottomBar
                ),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(classrooms) { classroom ->
                    ClassroomCard(classroom = classroom)
                }
            }
        }
    }
}
