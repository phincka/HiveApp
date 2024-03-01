package com.example.hiveapp.data.repository

import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.remote.weather.RemoteSource
import com.example.hiveapp.data.util.WeatherResource
import com.example.hiveapp.data.util.toWeatherInfo
import com.example.hiveapp.domain.repository.WeatherRepository
import org.koin.core.annotation.Single

@Single
class WeatherRepositoryImpl : WeatherRepository {
    private val api = RemoteSource.api

    override suspend fun getWeatherData(lat: Double, lng: Double): WeatherResource<Map<Int, List<WeatherModel>>> {
        return try {
            WeatherResource.Success(
                data = api.getWeatherData(lat, lng).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            WeatherResource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}


