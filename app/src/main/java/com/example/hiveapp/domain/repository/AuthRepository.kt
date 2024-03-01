package com.example.hiveapp.domain.repository

import com.example.hiveapp.data.model.UserModel
import com.example.hiveapp.data.util.AuthState


//typealias AuthStateResponse = StateFlow<Boolean>
//typealias SignInResponse = SignInState
//typealias SignOutResponse = AuthResponse<Boolean>

interface AuthRepository {
    suspend fun getCurrentUser(): UserModel?
    suspend fun firebaseEmailSignIn(email: String, password: String): AuthState
    suspend fun firebaseSignOut(): AuthState
}