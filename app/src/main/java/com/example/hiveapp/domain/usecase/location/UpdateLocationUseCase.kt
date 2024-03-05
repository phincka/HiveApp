package com.example.hiveapp.domain.usecase.location

import com.example.hiveapp.domain.repository.HiveRepository
import org.koin.core.annotation.Single

@Single
class UpdateLocationUseCase(
    private val hiveRepository: HiveRepository
) {
    suspend operator fun invoke(id: String, lat: Double, lng: Double) = hiveRepository.updateHiveLocation(id, lat, lng)
}

