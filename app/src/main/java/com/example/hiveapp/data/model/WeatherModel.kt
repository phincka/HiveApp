package com.example.hiveapp.data.model

import com.example.hiveapp.data.util.WeatherType

data class WeatherModel(
    val time: String,
    val temperatureCelsius: Double,
    val pressure: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
