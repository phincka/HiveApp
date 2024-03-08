package com.example.hiveapp.data.repository

import android.content.Context
import com.example.hiveapp.data.dao.HiveDao
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import com.example.hiveapp.di.DISPATCHER_IO
import com.example.hiveapp.domain.repository.HiveRepository
import kotlinx.coroutines.CoroutineDispatcher
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Single
class HiveRepositoryImpl(
    private val hiveDao: HiveDao,
): HiveRepository {
    override suspend fun getAllHives(): List<HiveModel> {
        return hiveDao.getAllHives()
    }

    override suspend fun getHiveById(id: Int): HiveModel? {
        return hiveDao.getHiveById(id)
    }
    override suspend fun insertHive(hive: HiveModel) {
        hiveDao.insertHive(hive)
    }

    override suspend fun updateHive(hive: HiveModel) {
        hiveDao.updateHive(hive)
    }

    override suspend fun deleteHives(hives: List<HiveModel>) {
        hiveDao.deleteHives(hives)
    }

    override suspend fun getHivesLocations(): List<HiveLocationModel> {
        return hiveDao.getHivesLocations()
    }

    override suspend fun getLocationByHiveId(id: Int): List<HiveLocationModel> {
        return hiveDao.getLocationByHiveId(id)
    }

    override suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double) {
        hiveDao.updateHiveLocation(id, lat, lng)
    }
}