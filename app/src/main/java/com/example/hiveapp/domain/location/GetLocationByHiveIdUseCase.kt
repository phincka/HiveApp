package com.example.hiveapp.domain.location

import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class GetLocationByHiveIdUseCase(private val hiveRepository: HiveRepository) {
    suspend operator fun invoke(id: Int): List<HiveLocationModel> {
        return hiveRepository.getLocationByHiveId(id)
    }
}

