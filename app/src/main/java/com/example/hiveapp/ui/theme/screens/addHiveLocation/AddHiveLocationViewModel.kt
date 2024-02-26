package com.example.hiveapp.ui.theme.screens.addHiveLocation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.repository.HiveRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddHiveLocationViewModel(private val hiveRepository: HiveRepository) : ViewModel() {
    val getHivesLocations: Flow<List<HiveLocationModel>> = hiveRepository.getHivesLocations()
    var mapState by mutableStateOf(MapState())

    fun updateHiveLocation(id: Int, lat: Double, lng: Double) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            hiveRepository.updateHiveLocation(id, lat, lng)
        }
    }

}