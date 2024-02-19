package com.example.hiveapp.data.remote.weather

import com.squareup.moshi.Json

data class WeatherDataDto(
    val time: List<String>,

    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,

    @field:Json(name = "weather_code")
    val weatherCodes: List<Int>,

    @field:Json(name = "rain")
    val pressures: List<Double>,

    @field:Json(name = "relative_humidity_2m")
    val humidities: List<Double>
)

