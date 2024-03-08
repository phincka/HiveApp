package com.example.hiveapp.data.remote.weather

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,rain,weather_code")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double
    ): WeatherDto
}