package com.example.hiveapp.data.repository

import com.example.hiveapp.data.dao.HiveDao
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HiveRepository(
    private val hiveDao: HiveDao
) {
    fun getAllHives(): Flow<List<HiveModel>> {
        return hiveDao.getAllHives()
    }

    fun getHiveById(id: Int): Flow<List<HiveModel>> {
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
    fun getHivesLocations(): Flow<List<HiveLocationModel>> {
        return hiveDao.getHivesLocations()
    }

    fun getLocationByHiveId(id: Int): Flow<List<HiveLocationModel>> {
        return hiveDao.getLocationByHiveId(id)
    }

    suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double) = withContext(Dispatchers.IO ) {
        hiveDao.updateHiveLocation(id, lat, lng)
    }
}