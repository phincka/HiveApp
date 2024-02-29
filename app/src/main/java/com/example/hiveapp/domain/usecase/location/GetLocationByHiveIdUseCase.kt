package com.example.hiveapp.domain.usecase.location

import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.domain.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class GetLocationByHiveIdUseCase(
    private val hiveRepository: HiveRepository
) {
    suspend operator fun invoke(id: Int): List<HiveLocationModel> {
        return hiveRepository.getLocationByHiveId(id)
    }
}

