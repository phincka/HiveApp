package com.example.hiveapp.domain.usecase.hive

import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class GetHiveByIdUseCase(
    private val hiveRepository: HiveRepository
) {
    suspend operator fun invoke(id: String): HiveModel? {
        return hiveRepository.getHiveById(id)
    }
}