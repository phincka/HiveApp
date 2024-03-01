package com.example.hiveapp.domain.usecase.weather

import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.util.WeatherResource
import com.example.hiveapp.domain.repository.WeatherRepository
import org.koin.core.annotation.Single

@Single
class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double): WeatherResource<Map<Int, List<WeatherModel>>> {
        return weatherRepository.getWeatherData(lat, lng)
    }
}