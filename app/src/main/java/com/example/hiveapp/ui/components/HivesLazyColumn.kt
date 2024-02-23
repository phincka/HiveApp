package com.example.hiveapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import com.example.hiveapp.data.model.HiveModel
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun HivesLazyColumn(
    hives: List<HiveModel>,
    navigator : DestinationsNavigator
) {
    Divider()
    LazyColumn() {
        items(items = hives, key = { it.id }) { hive ->
            HiveRow(hive, navigator)
            Divider()
        }
    }
}