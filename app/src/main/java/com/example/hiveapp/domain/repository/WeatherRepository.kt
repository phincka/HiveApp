package com.example.hiveapp.domain.repository

import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lng: Double): Resource<Map<Int, List<WeatherModel>>>
}