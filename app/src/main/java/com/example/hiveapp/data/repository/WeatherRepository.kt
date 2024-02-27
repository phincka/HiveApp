package com.example.hiveapp.data.repository

import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.remote.weather.RemoteSource
import com.example.hiveapp.data.util.Resource
import com.example.hiveapp.data.util.toWeatherInfo
import org.koin.core.annotation.Single

@Single
class WeatherRepository {
    private val api = RemoteSource.api

    suspend fun getWeatherData(lat: Double, lng: Double): Resource<Map<Int, List<WeatherModel>>> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat, lng).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}


