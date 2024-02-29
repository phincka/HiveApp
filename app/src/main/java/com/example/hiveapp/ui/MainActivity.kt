package com.example.hiveapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.hiveapp.ui.theme.HiveAppTheme
import com.example.hiveapp.ui.theme.screens.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HiveAppTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}
