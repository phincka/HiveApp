package com.example.hiveapp.ui.theme.screens.addHiveLocation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.domain.usecase.location.GetHivesLocationsUseCase
import com.example.hiveapp.domain.usecase.location.UpdateLocationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class AddHiveLocationViewModel(
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val getHivesLocationsUseCase: GetHivesLocationsUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<AddHiveLocationState> = MutableStateFlow(AddHiveLocationState.Loading)
    val addHiveLocationState: StateFlow<AddHiveLocationState> = _state
    var mapState by mutableStateOf(MapState())

    init {
        getHivesLocations()
    }

    private fun getHivesLocations() {
        viewModelScope.launch {
            try {
                val locations = getHivesLocationsUseCase()
                _state.value = AddHiveLocationState.Success(locations)
            } catch (e: Exception) {
                _state.value = AddHiveLocationState.Error("Failed: ${e.message}")
            }
        }
    }

    fun updateHiveLocation(
        id: String,
        lat: Double,
        lng: Double,
    ) {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            updateLocationUseCase(id, lat, lng)
        }
        viewModelScope.launch {
            try {
                updateLocationUseCase(id, lat, lng)

                val locations = (_state.value as AddHiveLocationState.Success).locations
                val updatedLocations = locations.map { location ->
                    if (location.id == id) {
                        location.copy(lat = lat, lng = lng)
                    } else {
                        location
                    }
                }

                _state.value = AddHiveLocationState.Success(updatedLocations)
            } catch (e: Exception) {
                _state.value = AddHiveLocationState.Error("Failed: ${e.message}")
            }
        }
    }
}