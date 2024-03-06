package com.example.hiveapp.domain.repository

import com.example.hiveapp.ui.theme.screens.weatherScreen.WeatherState

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lng: Double): WeatherState
}