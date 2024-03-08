package com.example.hiveapp.di

import com.example.hiveapp.data.remote.weather.WeatherApi
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class RestModule {
    @Single
    @Named(WEATHER_RETROFIT)
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.open-meteo.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Single
    fun provideWeatherApi(@Named(WEATHER_RETROFIT) retrofit: Retrofit): WeatherApi = retrofit.create(WeatherApi::class.java)
}

const val WEATHER_RETROFIT = "Weather API"



