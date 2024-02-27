package com.example.hiveapp.ui.theme.screens.hive

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.hive.DeleteHivesUseCase
import com.example.hiveapp.domain.hive.GetHiveByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HiveViewModel(
    savedStateHandle: SavedStateHandle,
    private val getHiveByIdUseCase: GetHiveByIdUseCase,
    private val deleteHivesUseCase: DeleteHivesUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<HiveState> = MutableStateFlow(HiveState.Loading)
    val hiveState: StateFlow<HiveState> = _state
    var removeState by mutableStateOf(false)

    init {
        val id = savedStateHandle.get<Int>("id")

        if (id != null) {
            getHiveById(id)
        } else {
            _state.value = HiveState.Error("Failed: Hive ID not provided")
        }
    }

    private fun getHiveById(id: Int) {
        viewModelScope.launch {
            try {
                val hives = getHiveByIdUseCase(id)

                if (hives.isNotEmpty()) {
                    _state.value = HiveState.Success(hives[0])
                } else {
                    _state.value = HiveState.Error("Failed: There is no hive with the given ID")
                }
            } catch (e: Exception) {
                _state.value = HiveState.Error("Failed: ${e.message}")
            }
        }
    }

    fun removeHive(hive: List<HiveModel>) = CoroutineScope(viewModelScope.coroutineContext).launch {
        viewModelScope.launch {
            try {
                deleteHivesUseCase(hive)
                removeState = true
            } catch (e: Exception) {
                _state.value = HiveState.Error("Failed: ${e.message}")
            }
        }
    }
}