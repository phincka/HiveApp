package com.example.hiveapp.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Warning
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.rememberNavController
import com.example.hiveapp.R
import com.example.hiveapp.ui.theme.HiveAppTheme
import com.example.hiveapp.ui.theme.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

                HiveAppTheme {
                    DestinationsNavHost(
//                        navController = navController,
                        navGraph = NavGraphs.root
                    )
                }
        }
    }
}

val bottomNavigationItems = listOf(
    BottomNavigationScreens.Home,
    BottomNavigationScreens.CreateHive,
)

sealed class BottomNavigationScreens(val route: String, @StringRes val resourceId: Int, val icon: ImageVector) {
    data object Home :
        BottomNavigationScreens("Home", R.string.home_screen, Icons.Filled.Home)

    data object CreateHive : BottomNavigationScreens(
        "CreateHive",
        R.string.hive_screen,
        Icons.Filled.Warning
    )
}
