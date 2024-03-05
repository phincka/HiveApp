package com.example.hiveapp.ui.theme.screens.hive

import com.example.hiveapp.data.model.HiveModel

sealed class HiveState {
    data object Loading : HiveState()
    data class Success(val hive: HiveModel, val hasLocation: Boolean) : HiveState()
    data class Error(val message: String) : HiveState()
}