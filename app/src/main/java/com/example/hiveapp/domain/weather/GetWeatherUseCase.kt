package com.example.hiveapp.domain.weather

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.hiveapp.data.model.WeatherModel
import com.example.hiveapp.data.repository.WeatherRepository
import com.example.hiveapp.data.util.Resource
import org.koin.core.annotation.Single

@Single
class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {
    suspend operator fun invoke(lat: Double, lng: Double): Resource<Map<Int, List<WeatherModel>>> {
        return weatherRepository.getWeatherData(lat, lng)
    }
}