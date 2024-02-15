package com.example.hiveapp.data.repository

import com.example.hiveapp.data.dao.HiveDao
import com.example.hiveapp.data.model.Hive
import com.example.hiveapp.data.model.HiveLocation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class HiveRepository(
    private val hiveDao: HiveDao
) {
    fun getAll(): Flow<List<Hive>> {
        return hiveDao.getAll()
    }

    fun getHive(id: Int): Flow<List<Hive>> {
        return hiveDao.getHive(id)
    }
    suspend fun insert(hive: Hive) = withContext(Dispatchers.IO) {
        hiveDao.insert(hive)
    }

    suspend fun update(hive: Hive) = withContext(Dispatchers.IO ) {
        hiveDao.update(hive)
    }

    suspend fun delete(hives: List<Hive>) = withContext(Dispatchers.IO) {
        hiveDao.delete(hives)
    }
    fun getHivesLocations(): Flow<List<HiveLocation>> {
        return hiveDao.getHivesLocations()
    }

    suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double) = withContext(Dispatchers.IO ) {
        hiveDao.updateHiveLocation(id, lat, lng)
    }
}