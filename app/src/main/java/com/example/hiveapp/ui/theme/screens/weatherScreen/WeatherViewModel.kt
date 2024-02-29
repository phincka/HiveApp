package com.example.hiveapp.ui.theme.screens.weatherScreen

import androidx.lifecycle.SavedStateHandle
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
    savedStateHandle: SavedStateHandle,
    private val getLocationByHiveIdUseCase: GetLocationByHiveIdUseCase,
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {
    private val _weatherState: MutableStateFlow<WeatherState> = MutableStateFlow(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    private val _hiveLocationState: MutableStateFlow<HiveLocationState> = MutableStateFlow(HiveLocationState.Loading)
    private val hiveLocationState: StateFlow<HiveLocationState> = _hiveLocationState

    init {
        val id = savedStateHandle.get<Int>("id")

        if (id != null) {
            getLocationByHiveId(id)
        } else {
            _hiveLocationState.value = HiveLocationState.Error("Failed: Hive ID not provided")
        }

        viewModelScope.launch {
            hiveLocationState.collect { state ->
                when (state) {
                    is HiveLocationState.Success -> {
                        val hiveLocation = state.hiveLocation
                        getWeather(hiveLocation.lat, hiveLocation.lng)
                    }
                    is HiveLocationState.Loading -> {
                        println("ładowanie")
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
        viewModelScope.launch {
            try {
                val location = getLocationByHiveIdUseCase(id)[0]

                if (location.lat != 0.0 && location.lng != 0.0) {
                    _hiveLocationState.value = HiveLocationState.Success(location)
                } else {
                    _hiveLocationState.value = HiveLocationState.Error("Failed: Hive hasn't location")
                }
            } catch (e: Exception) {
                _hiveLocationState.value = HiveLocationState.Error("Failed: ${e.message}")
            }
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


