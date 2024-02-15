package com.example.hiveapp.ui.theme.screens.addHiveLocation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.data.model.HiveLocation
import com.example.hiveapp.data.repository.HiveRepository
import com.example.hiveapp.ui.theme.screens.addHiveLocation.MapState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AddHiveLocationViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    val getHivesLocations: Flow<List<HiveLocation>> = hiveRepository.getHivesLocations()

    fun updateHiveLocation(id: Int, lat: Double, lng: Double) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            hiveRepository.updateHiveLocation(id, lat, lng)
        }
    }

    var state by mutableStateOf(MapState())
}