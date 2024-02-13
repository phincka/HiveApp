package com.example.hiveapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.hiveapp.data.model.Hive

@Composable
fun HivesLazyColumn(hives: List<Hive>, navController : NavController) {
    Divider()
    LazyColumn() {
        items(items = hives, key = { it.id }) { hive ->
            HiveRow(hive, navController)
            Divider()
        }
    }
}