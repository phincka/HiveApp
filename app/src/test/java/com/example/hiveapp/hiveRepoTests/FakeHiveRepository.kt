package com.example.hiveapp.hiveRepoTests

import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.domain.repository.HiveRepository

class FakeHiveRepository : HiveRepository {
    private val hives = mutableListOf<HiveModel>()
    override suspend fun getAllHives(): List<HiveModel> {
        return hives
    }

    override suspend fun getHiveById(id: Int): HiveModel? {
        return hives.find { it.id == id }
    }

    override suspend fun insertHive(hive: HiveModel) {
        hives.add(hive)
    }

    override suspend fun updateHive(hive: HiveModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteHives(hives: List<HiveModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun getHivesLocations(): List<HiveLocationModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getLocationByHiveId(id: Int): List<HiveLocationModel> {
        TODO("Not yet implemented")
    }

    override suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double) {
        TODO("Not yet implemented")
    }

}