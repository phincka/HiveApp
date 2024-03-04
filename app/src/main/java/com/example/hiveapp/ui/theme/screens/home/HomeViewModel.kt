package com.example.hiveapp.ui.theme.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.model.UserModel
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.usecase.auth.GetCurrentUserUseCase
import com.example.hiveapp.domain.usecase.auth.SignOutUseCase
import com.example.hiveapp.domain.usecase.hive.GetAllHivesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val getAllHivesUseCase: GetAllHivesUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : ViewModel() {
    private val _homeState: MutableStateFlow<HomeState> = MutableStateFlow(HomeState.Loading)
    val homeState: StateFlow<HomeState> = _homeState

    private val _user = MutableStateFlow<UserModel?>(null)
    val user = _user.asStateFlow()

    private val _signOutState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Success(false))
    val signOutState: StateFlow<AuthState> = _signOutState

    init {
        getCurrentUser()
        getAllHives()
    }

    private fun getCurrentUser() {
        viewModelScope.launch {
            try {
                val user = getCurrentUserUseCase()
                if (user != null) {
                    _user.value = user
                }
            } catch (e: Exception) {
                _user.value = null
            }
        }
    }

    private fun getAllHives() {
        viewModelScope.launch {
            try {
                val hives = getAllHivesUseCase()
                _homeState.value = HomeState.Success(hives)
            } catch (e: Exception) {
                _homeState.value = HomeState.Error("Failed: ${e.message}")
            }
        }
    }

    fun signOut() = viewModelScope.launch {
        _signOutState.value = AuthState.Loading
        _signOutState.value = signOutUseCase()
    }
}