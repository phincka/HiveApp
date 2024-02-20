package com.example.hiveapp.ui.theme.screens.weatherScreen

import com.example.hiveapp.data.model.WeatherModel

data class WeatherState(
    val today: WeatherModel? = null,
    val hourly: List<WeatherModel>? = null,
    val daily: List<Map.Entry<Int, List<WeatherModel>>>? = null,
    val loading: Boolean? = true
)
