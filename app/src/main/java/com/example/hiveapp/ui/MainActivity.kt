package com.example.hiveapp.ui

import NavigationGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.navigation.compose.rememberNavController
import com.example.hiveapp.ui.theme.HiveAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            HiveAppTheme() {
                NavigationGraph(navController)
            }
        }
    }
}
