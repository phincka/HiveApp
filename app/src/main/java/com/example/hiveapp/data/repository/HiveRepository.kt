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
    fun getAll(): Flow<List<HiveModel>> {
        return hiveDao.getAll()
    }

    fun getHive(id: Int): Flow<List<HiveModel>> {
        return hiveDao.getHive(id)
    }
    suspend fun insert(hive: HiveModel) = withContext(Dispatchers.IO) {
        hiveDao.insert(hive)
    }

    suspend fun update(hive: HiveModel) = withContext(Dispatchers.IO ) {
        hiveDao.update(hive)
    }

    suspend fun delete(hives: List<HiveModel>) = withContext(Dispatchers.IO) {
        hiveDao.delete(hives)
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