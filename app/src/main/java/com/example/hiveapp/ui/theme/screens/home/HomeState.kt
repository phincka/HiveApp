package com.example.hiveapp.ui.theme.screens.home

import com.example.hiveapp.data.model.HiveModel

sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val hives: List<HiveModel>) : HomeState()
    data class Error(val message: String) : HomeState()
}