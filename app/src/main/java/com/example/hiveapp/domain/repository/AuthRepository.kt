package com.example.hiveapp.domain.repository

import com.example.hiveapp.data.model.UserModel
import com.example.hiveapp.data.util.AuthState


interface AuthRepository {
    suspend fun getCurrentUser(): UserModel?
    suspend fun firebaseEmailSignIn(email: String, password: String): AuthState
    suspend fun firebaseEmailSignUp(email: String, password: String, repeatPassword: String): AuthState
    suspend fun firebaseSignOut(): AuthState
    suspend fun checkEmailVerification(): AuthState
    suspend fun resendVerificationEmail(): AuthState

}