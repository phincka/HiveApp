package com.example.hiveapp.domain.usecase.hive

import com.example.hiveapp.domain.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class DeleteHiveUseCase(
    private val hiveRepository: HiveRepository
) {
    suspend operator fun invoke(id: String) = hiveRepository.deleteHive(id)
}