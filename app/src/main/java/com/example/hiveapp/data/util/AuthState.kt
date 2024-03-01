package com.example.hiveapp.data.util

//data class SignInState(
//    val isSignInSuccessful: Boolean = false,
//    val signInError: String? = null
//)

sealed class AuthState {
    data object Loading: AuthState()

    data class Success(val success: Boolean): AuthState()

    data class Error(val error: Exception): AuthState()
}
