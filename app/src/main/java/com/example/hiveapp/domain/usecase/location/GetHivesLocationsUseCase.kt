package com.example.hiveapp.domain.usecase.location

import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.domain.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class GetHivesLocationsUseCase(
    private val hiveRepository: HiveRepository
) {
    suspend operator fun invoke(): List<HiveLocationModel> {
        return hiveRepository.getHivesLocations()
    }
}

