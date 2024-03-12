package com.example.hiveapp.ui.components

import androidx.annotation.StringRes
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.hiveapp.R
import com.example.hiveapp.ui.theme.screens.NavGraphs
import com.example.hiveapp.ui.theme.screens.appCurrentDestinationAsState
import com.example.hiveapp.ui.theme.screens.destinations.CreateEditHiveScreenDestination
import com.example.hiveapp.ui.theme.screens.destinations.Destination
import com.example.hiveapp.ui.theme.screens.destinations.HomeScreenDestination
import com.example.hiveapp.ui.theme.screens.startAppDestination
import com.ramcosta.composedestinations.navigation.navigateTo
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

@Composable
fun BottomBar(
    navController: NavController
) {
    val currentDestination: Destination = navController.appCurrentDestinationAsState().value
        ?: NavGraphs.root.startAppDestination

    BottomNavigation {
        BottomBarDestination.entries.forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigateTo(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = { Icon(destination.icon, contentDescription = stringResource(destination.label))},
                label = { Text(stringResource(destination.label)) },
            )
        }
    }
}

enum class BottomBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, Icons.Default.Home, R.string.home_screen),
    CreateEditHive(CreateEditHiveScreenDestination, Icons.Default.Build, R.string.hive_screen),
}