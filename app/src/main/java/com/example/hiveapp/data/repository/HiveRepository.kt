package com.example.hiveapp.data.repository

import com.example.hiveapp.data.dao.HiveDao
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Single

@Single
class HiveRepository(
    private val hiveDao: HiveDao
) {
    suspend fun getAllHives(): List<HiveModel> {
        return hiveDao.getAllHives()
    }

    suspend fun getHiveById(id: Int): List<HiveModel> {
        return hiveDao.getHiveById(id)
    }
    suspend fun insertHive(hive: HiveModel) = withContext(Dispatchers.IO) {
        hiveDao.insertHive(hive)
    }

    suspend fun updateHive(hive: HiveModel) = withContext(Dispatchers.IO ) {
        hiveDao.updateHive(hive)
    }

    suspend fun deleteHives(hives: List<HiveModel>) = withContext(Dispatchers.IO) {
        hiveDao.deleteHives(hives)
    }

    suspend fun getHivesLocations(): List<HiveLocationModel> {
        return hiveDao.getHivesLocations()
    }

    suspend fun getLocationByHiveId(id: Int): List<HiveLocationModel> {
        return hiveDao.getLocationByHiveId(id)
    }

    suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double) = withContext(Dispatchers.IO ) {
        hiveDao.updateHiveLocation(id, lat, lng)
    }
}