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
import androidx.navigation.NavController
import com.example.hiveapp.data.model.HiveModel

@Composable
fun HiveRow(
    hive: HiveModel,
    navController : NavController
) {
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        onClick = {
            navController.navigate("hive/${hive.id}")
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