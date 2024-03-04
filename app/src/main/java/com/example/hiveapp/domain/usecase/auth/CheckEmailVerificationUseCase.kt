package com.example.hiveapp.domain.usecase.auth

import com.example.hiveapp.domain.repository.AuthRepository
import org.koin.core.annotation.Single

@Single
class CheckEmailVerificationUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.checkEmailVerification()
}