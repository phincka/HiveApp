package com.example.hiveapp.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.ui.theme.screens.destinations.HiveScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun HiveRow(
    hive: HiveModel,
    navigator : DestinationsNavigator
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        onClick = {
            navigator.navigate(
                HiveScreenDestination(id = hive.id)
            )
        }
    ){
        ListItem(
            headlineContent = { Text(hive.name) },
            overlineContent = { Text("ID: ${hive.id}, QueenId: ${hive.line}") },
            trailingContent = { Icon(
                Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = null,
            ) },
        )
    }
}