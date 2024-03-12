package com.example.hiveapp.ui.theme.screens.signUp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.usecase.auth.SignUpUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
): ViewModel() {
    private val _state: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Success(false))
    val signUpState: StateFlow<AuthState> = _state

    fun signUp(
        email: String,
        password: String,
        repeatPassword: String
    ) {
        viewModelScope.launch {
            _state.value = AuthState.Loading
            _state.value = signUpUseCase(email, password, repeatPassword)
        }
    }
}