package com.example.hiveapp.ui.theme.screens.signIn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.usecase.auth.SignInUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignInViewModel(
    private val signInUseCase: SignInUseCase,
): ViewModel() {
    private val _state: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Success(false))
    val signInState: StateFlow<AuthState> = _state


    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            _state.value = signInUseCase(email, password)
        }
    }
}