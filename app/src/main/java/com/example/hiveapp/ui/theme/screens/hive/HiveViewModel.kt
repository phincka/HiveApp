package com.example.hiveapp.ui.theme.screens.hive

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.domain.usecase.hive.DeleteHiveUseCase
import com.example.hiveapp.domain.usecase.hive.GetHiveByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HiveViewModel(
    savedStateHandle: SavedStateHandle,
    private val getHiveByIdUseCase: GetHiveByIdUseCase,
    private val deleteHiveUseCase: DeleteHiveUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<HiveState> = MutableStateFlow(HiveState.Loading)
    val hiveState: StateFlow<HiveState> = _state
    var removeState by mutableStateOf(false)

    init {
        val id = savedStateHandle.get<String>("id")

        if (id != null) {
            getHiveById(id)
        } else {
            _state.value = HiveState.Error("Failed: Hive ID not provided")
        }
    }

    fun getHiveById(id: String) {
        _state.value = HiveState.Loading

        viewModelScope.launch {
            try {
                val hive = getHiveByIdUseCase(id)
                var hasLocation = false

                if (hive != null) {
                    if (hive.lat > 0 && hive.lng > 0) {
                        hasLocation = true
                    }

                    _state.value = HiveState.Success(hive, hasLocation)
                } else {
                    _state.value = HiveState.Error("Failed: There is no hive with the given ID")
                }
            } catch (e: Exception) {
                _state.value = HiveState.Error("Failed: ${e.message}")
            }
        }
    }

    fun removeHive(id: String) {
        _state.value = HiveState.Loading

        viewModelScope.launch {
            try {
                _state.value = HiveState.Loading
                deleteHiveUseCase(id)

                removeState = true
            } catch (e: Exception) {
                _state.value = HiveState.Error("Failed: ${e.message}")
            }
        }
    }
}