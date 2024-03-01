package com.example.hiveapp.domain.repository

import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.util.WeatherResource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lng: Double): WeatherResource<Map<Int, List<WeatherModel>>>
}