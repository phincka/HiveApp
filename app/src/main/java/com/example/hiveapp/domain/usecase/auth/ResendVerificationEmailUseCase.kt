package com.example.hiveapp.domain.usecase.auth

import com.example.hiveapp.domain.repository.AuthRepository
import org.koin.core.annotation.Single

@Single
class ResendVerificationEmailUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() = authRepository.resendVerificationEmail()
}