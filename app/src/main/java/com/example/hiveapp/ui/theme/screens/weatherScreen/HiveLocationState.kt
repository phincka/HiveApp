package com.example.hiveapp.ui.theme.screens.weatherScreen

import com.example.hiveapp.data.model.HiveLocationModel

sealed class HiveLocationState {
    data object Loading : HiveLocationState()
    data class Success(val hiveLocation: HiveLocationModel) : HiveLocationState()
    data class Info(val message: String) : HiveLocationState()
    data class Error(val message: String) : HiveLocationState()
}