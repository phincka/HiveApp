package com.example.hiveapp.ui.theme.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.domain.usecase.hive.GetAllHivesUseCase
import com.example.hiveapp.domain.usecase.notification.HiveCreatedNotificationUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val getAllHivesUseCase: GetAllHivesUseCase
) : ViewModel() {
    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _state

    init {
        getAllHives()
    }

    private fun getAllHives() {
        _state.value = HomeState.Loading

        viewModelScope.launch {
            try {
                val hives = getAllHivesUseCase()

                _state.value = HomeState.Success(hives)
            } catch (e: Exception) {
                _state.value = HomeState.Error("Failed: ${e.message}")
            }
        }
    }
}