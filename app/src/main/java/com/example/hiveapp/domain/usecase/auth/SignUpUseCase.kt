package com.example.hiveapp.domain.usecase.auth

import com.example.hiveapp.domain.repository.AuthRepository
import org.koin.core.annotation.Single

@Single
class SignUpUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String, repeatPassword: String) = authRepository.firebaseEmailSignUp(email, password, repeatPassword)
}