package com.example.hiveapp.domain.usecase

import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class GetHiveByIdUseCase(private val hiveRepository: HiveRepository) {
    suspend operator fun invoke(id: Int): List<HiveModel> {
        return hiveRepository.getHiveById(id)
    }
}