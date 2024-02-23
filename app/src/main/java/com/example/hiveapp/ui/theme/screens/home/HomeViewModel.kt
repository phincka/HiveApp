package com.example.hiveapp.ui.theme.screens.home

import androidx.lifecycle.ViewModel
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.flow.Flow

class HomeViewModel(
    hiveRepository: HiveRepository
) : ViewModel() {
    val getAllHives: Flow<List<HiveModel>> = hiveRepository.getAllHives()

//    @RequiresApi(Build.VERSION_CODES.O)
//    fun getWeather(lat: Double, lng: Double) = viewModelScope.launch {
//        val remote = weatherRepository.getWeatherData(lat, lng)
//
//        if (!remote.data.isNullOrEmpty()) {
//            println("-------------------------------")
//            println(remote.data[0]?.get(0)?.weatherType?.weatherDesc)
//            println("-------------------------------")
//        }
//    }
}