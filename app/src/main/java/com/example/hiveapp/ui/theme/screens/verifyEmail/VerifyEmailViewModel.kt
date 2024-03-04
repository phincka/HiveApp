package com.example.hiveapp.ui.theme.screens.verifyEmail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hiveapp.data.util.AuthState
import com.example.hiveapp.domain.usecase.auth.CheckEmailVerificationUseCase
import com.example.hiveapp.domain.usecase.auth.ResendVerificationEmailUseCase
import com.example.hiveapp.domain.usecase.auth.SignOutUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class VerifyEmailViewModel(
    private val checkEmailVerificationUseCase: CheckEmailVerificationUseCase,
    private val resendVerificationEmailUseCase: ResendVerificationEmailUseCase,
    private val signOutUseCase: SignOutUseCase
): ViewModel() {
    private val _verificationEmailState: MutableStateFlow<AuthState> = MutableStateFlow(AuthState.Success(false))
    val verificationEmailState: StateFlow<AuthState> = _verificationEmailState

    fun checkEmailVerification() = viewModelScope.launch {
        _verificationEmailState.value = AuthState.Loading
        _verificationEmailState.value = checkEmailVerificationUseCase()
    }

    fun resendVerificationEmail() = viewModelScope.launch {
        _verificationEmailState.value = AuthState.Loading
        _verificationEmailState.value = resendVerificationEmailUseCase()
    }

    fun signOut() = viewModelScope.launch {
        _verificationEmailState.value = AuthState.Loading
        _verificationEmailState.value = signOutUseCase()
    }
}