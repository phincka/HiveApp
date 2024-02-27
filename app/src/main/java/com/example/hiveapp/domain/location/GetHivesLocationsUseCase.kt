package com.example.hiveapp.domain.location

import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class GetHivesLocationsUseCase(private val hiveRepository: HiveRepository) {
    suspend operator fun invoke(): List<HiveLocationModel> {
        return hiveRepository.getHivesLocations()
    }
}

