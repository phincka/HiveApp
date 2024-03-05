package com.example.hiveapp.ui.theme.screens.weatherScreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.domain.usecase.weather.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WeatherViewModel(
    savedStateHandle: SavedStateHandle,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _weatherState: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    init {
        val hasLocation: Boolean = savedStateHandle.get<Boolean>("hasLocation") ?: false
        val lat: Double = savedStateHandle.get<Double>("lat") ?: 0.0
        val lng: Double = savedStateHandle.get<Double>("lng") ?: 0.0

        if (hasLocation && lat != 0.0 && lng != 0.0) {
            getWeather(lat, lng)
        } else {
            _weatherState.value = WeatherState.Error("Ul nie posiada lokalizacji")
        }
    }

    private fun getWeather(lat: Double, lng: Double) {
        viewModelScope.launch {
            try {
                val remoteWeatherData = getWeatherUseCase(lat, lng)

                if (!remoteWeatherData.data.isNullOrEmpty()) {
                    val today = remoteWeatherData.data[0]?.get(11)
                    val hourly = remoteWeatherData.data[0]
                    val daily = remoteWeatherData.data.entries.take(12)

                    _weatherState.value = WeatherState.Success(
                        today = today,
                        hourly = hourly,
                        daily = daily,
                    )
                }
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error("Failed: ${e.message}")
            }
        }
    }
}