package com.example.hiveapp.ui.theme.screens.hive

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.usecase.hive.DeleteHivesUseCase
import com.example.hiveapp.domain.usecase.hive.GetHiveByIdUseCase
import com.example.hiveapp.domain.usecase.notification.CreateLocationNotificationUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HiveViewModel(
    id: Int,
    private val getHiveByIdUseCase: GetHiveByIdUseCase,
    private val deleteHivesUseCase: DeleteHivesUseCase,
    private val createLocationNotificationUseCase: CreateLocationNotificationUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<HiveState> = MutableStateFlow(HiveState.Loading)
    val hiveState: StateFlow<HiveState> = _state
    var removeState by mutableStateOf(false)

    init {
        getHiveById(id)
    }

    private fun getHiveById(id: Int) {
        _state.value = HiveState.Loading

        viewModelScope.launch {
            try {
                val hive = getHiveByIdUseCase(id)

                if (hive != null) {
                    _state.value = HiveState.Success(hive)
                } else {
                    _state.value = HiveState.Info("There is no hive with the given ID")
                }
            } catch (e: Exception) {
                _state.value = HiveState.Error("Failed: ${e.message}")
            }
        }
    }

    fun removeHive(hive: List<HiveModel>) = CoroutineScope(viewModelScope.coroutineContext).launch {
        _state.value = HiveState.Loading

        viewModelScope.launch {
            try {
                deleteHivesUseCase(hive)
                removeState = true
            } catch (e: Exception) {
                _state.value = HiveState.Error("Failed: ${e.message}")
            }
        }
    }

    fun hiveCreatedNotification() = createLocationNotificationUseCase()
}