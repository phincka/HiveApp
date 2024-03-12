package com.example.hiveapp.domain.repository

import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.data.util.AuthState

interface HiveRepository {

    suspend fun getAllHives(): List<HiveModel>

    suspend fun getHiveById(id: String): HiveModel?
    suspend fun insertHive(hive: HiveModel): AuthState

    suspend fun deleteHive(id: String): AuthState

    suspend fun getHivesLocations(): List<HiveLocationModel>

    suspend fun updateHiveLocation(id: String, lat: Double, lng: Double): AuthState
}