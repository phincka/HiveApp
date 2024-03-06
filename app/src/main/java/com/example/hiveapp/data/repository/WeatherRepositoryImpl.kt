package com.example.hiveapp.data.repository

import com.example.hiveapp.data.remote.weather.RemoteSource
import com.example.hiveapp.data.util.toWeatherInfo
import com.example.hiveapp.domain.repository.WeatherRepository
import com.example.hiveapp.ui.theme.screens.weatherScreen.WeatherState
import org.koin.core.annotation.Single

@Single
class WeatherRepositoryImpl : WeatherRepository {
    private val api = RemoteSource.api

    override suspend fun getWeatherData(lat: Double, lng: Double): WeatherState {
        return try {
            val dataFromApi = api.getWeatherData(lat, lng).toWeatherInfo()
            val today = dataFromApi[0]?.get(11)!!
            val hourly = dataFromApi[0]!!
            val daily = dataFromApi.entries.take(7)

            WeatherState.Success(
                today,
                hourly,
                daily
            )
        } catch(error: Exception) {
            WeatherState.Error(error.message ?: "An unknown error occurred.")
        }
    }
}


