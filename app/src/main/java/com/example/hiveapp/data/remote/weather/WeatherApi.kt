package com.example.hiveapp.data.remote.weather

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("v1/forecast?hourly=temperature_2m,relative_humidity_2m,rain,weather_code")
    suspend fun getWeatherData(
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double
    ): WeatherDto
}

object RemoteSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api = retrofit.create(WeatherApi::class.java)
}