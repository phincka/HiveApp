package com.example.hiveapp.ui.theme.screens.addHiveLocation

import com.example.hiveapp.data.model.HiveLocationModel

sealed class AddHiveLocationState {
    data object Loading : AddHiveLocationState()
    data class Success(val locations: List<HiveLocationModel>) : AddHiveLocationState()
    data class Error(val message: String) : AddHiveLocationState()
}