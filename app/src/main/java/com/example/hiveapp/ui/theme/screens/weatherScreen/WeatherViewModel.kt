package com.example.hiveapp.ui.theme.screens.weatherScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.domain.usecase.location.GetLocationByHiveIdUseCase
import com.example.hiveapp.domain.usecase.weather.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class WeatherViewModel(
    id: Int,
    private val getLocationByHiveIdUseCase: GetLocationByHiveIdUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _weatherState: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    private val _hiveLocationState: MutableStateFlow<HiveLocationState> = MutableStateFlow(HiveLocationState.Loading)
    private val hiveLocationState: StateFlow<HiveLocationState> = _hiveLocationState

    init {
        _weatherState.value = WeatherState.Loading
        getLocationByHiveId(id)

        viewModelScope.launch {
            hiveLocationState.collect { state ->
                when (state) {
                    is HiveLocationState.Success -> {
                        val hiveLocation = state.hiveLocation
                        getWeather(hiveLocation.lat, hiveLocation.lng)
                    }

                    is HiveLocationState.Loading -> _weatherState.value = WeatherState.Loading

                    is HiveLocationState.Info -> {
                        val message = state.message
                        _weatherState.value = WeatherState.Info(message)
                    }

                    is HiveLocationState.Error -> {
                        val errorMessage = state.message
                        _weatherState.value = WeatherState.Error("Failed: $errorMessage")
                    }
                }
            }
        }
    }

    private fun getLocationByHiveId(id: Int) {
        _weatherState.value = WeatherState.Loading

        viewModelScope.launch {
            try {
                val location = getLocationByHiveIdUseCase(id)[0]

                if (location.lat != 0.0 && location.lng != 0.0) {
                    _hiveLocationState.value = HiveLocationState.Success(location)
                } else {
                    _hiveLocationState.value = HiveLocationState.Info("Hive hasn't location")
                }
            } catch (e: Exception) {
                _hiveLocationState.value = HiveLocationState.Error("Failed: ${e.message}")
            }
        }
    }

    private fun getWeather(lat: Double, lng: Double) {
        _weatherState.value = WeatherState.Loading

        viewModelScope.launch {
            try {
                _weatherState.value = getWeatherUseCase(lat, lng)
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error("Failed: ${e.message}")
            }
        }
    }
}


