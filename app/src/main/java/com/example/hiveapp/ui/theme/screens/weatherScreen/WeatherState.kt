package com.example.hiveapp.ui.theme.screens.weatherScreen

import com.example.hiveapp.data.model.WeatherModel

sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(
        val today: WeatherModel,
        val hourly: List<WeatherModel>,
        val daily: List<Map.Entry<Int, List<WeatherModel>>>,
    ) : WeatherState()
    data class Info(val message: String) : WeatherState()
    data class Error(val message: String) : WeatherState()
}