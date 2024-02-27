package com.example.hiveapp.ui.theme.screens.weatherScreen

import com.example.hiveapp.data.model.WeatherModel
sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(
        val today: WeatherModel? = null,
        val hourly: List<WeatherModel>? = null,
        val daily: List<Map.Entry<Int, List<WeatherModel>>>? = null,
    ) : WeatherState()
    data class Error(val message: String) : WeatherState()
}