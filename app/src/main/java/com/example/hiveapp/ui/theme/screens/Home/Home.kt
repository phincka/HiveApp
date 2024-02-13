package com.example.hiveapp.ui.theme.screens.Home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.getViewModel

@Composable
fun Home(navController: NavController) {
    val mainViewModel: HomeViewModel = getViewModel()

    Column {
        Text("HOME")
    }
}