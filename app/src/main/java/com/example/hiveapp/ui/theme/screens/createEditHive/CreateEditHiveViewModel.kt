package com.example.hiveapp.ui.theme.screens.createEditHive

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.usecase.hive.CreateHiveUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class CreateEditHiveViewModel(
    private val createHiveUseCase: CreateHiveUseCase
) : ViewModel() {
    private val _createHiveState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Success(false))
    val createHiveState: StateFlow<AuthState> = _createHiveState

    fun insertHive(hive: HiveModel) {
        viewModelScope.launch {
            _createHiveState.value = AuthState.Loading
            _createHiveState.value = createHiveUseCase(hive)
        }
    }
}