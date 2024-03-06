package com.example.hiveapp.domain.usecase.weather

import com.example.hiveapp.domain.repository.WeatherRepository
import com.example.hiveapp.ui.theme.screens.weatherScreen.WeatherState
import org.koin.core.annotation.Single

@Single
class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double): WeatherState {
        return weatherRepository.getWeatherData(lat, lng)
    }
}