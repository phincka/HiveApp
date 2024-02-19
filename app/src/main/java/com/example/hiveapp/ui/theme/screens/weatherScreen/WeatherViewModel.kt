package com.example.hiveapp.ui.theme.screens.weatherScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.repository.HiveRepository
import com.example.hiveapp.data.repository.WeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val hiveRepository: HiveRepository,
    private val weatherRepository: WeatherRepository
) : ViewModel() {
    fun getLocationByHiveId(id: Int): Flow<List<HiveLocationModel>> {
        return hiveRepository.getLocationByHiveId(id)
    }

    var state by mutableStateOf(WeatherState())
    @RequiresApi(Build.VERSION_CODES.O)
    fun getWeather(lat: Double, lng: Double) {
        viewModelScope.launch {
            val remote = weatherRepository.getWeatherData(lat, lng)

            if (!remote.data.isNullOrEmpty()) {
                val today = remote.data[0]?.get(0)
                val hourly = remote.data[0]?.take(8)
                val daily = remote.data[0]?.take(4)

                state = state.copy(
                    today = today,
                    hourly = hourly,
                    daily = daily,
                    loading = false
                )
            }
        }
    }

}