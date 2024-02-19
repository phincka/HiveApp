package com.example.hiveapp.data.model

import com.example.hiveapp.data.util.WeatherType
import java.time.LocalDateTime

data class WeatherModel(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val humidity: Double,
    val weatherType: WeatherType
)
