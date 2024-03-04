package com.example.hiveapp.domain.repository

import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel

interface HiveRepository {
    suspend fun getAllHives(): List<HiveModel>

    suspend fun getHiveById(id: String): HiveModel?
    suspend fun insertHive(hive: HiveModel)

    suspend fun updateHive(hive: HiveModel)

    suspend fun deleteHives(hives: List<HiveModel>)

    suspend fun getHivesLocations(): List<HiveLocationModel>
    suspend fun getLocationByHiveId(id: Int): List<HiveLocationModel>

    suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double)
}