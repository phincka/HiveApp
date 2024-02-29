package com.example.hiveapp.domain.usecase.hive

import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class GetAllHivesUseCase(
    private val hiveRepository: HiveRepository
) {
    suspend operator fun invoke(): List<HiveModel> {
        return hiveRepository.getAllHives()
    }
}