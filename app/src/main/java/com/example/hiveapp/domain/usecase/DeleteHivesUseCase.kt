package com.example.hiveapp.domain.usecase

import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class DeleteHivesUseCase(
    private val hiveRepository: HiveRepository
) {
    suspend operator fun invoke(hives: List<HiveModel>) {
        return hiveRepository.deleteHives(hives)
    }
}