package com.example.hiveapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel
import kotlinx.coroutines.flow.Flow

@Dao
interface HiveDao {
    @Insert
    suspend fun insertHive(hive: HiveModel)

    @Update
    suspend fun updateHive(hive: HiveModel)

    @Delete
    suspend fun deleteHives(hives: List<HiveModel>)

    @Query("SELECT * FROM hive_table")
    suspend fun getAllHives(): List<HiveModel>

    @Query("SELECT * FROM hive_table WHERE id = :id")
    suspend fun getHiveById(id: Int): List<HiveModel>

    @Query("SELECT id, name, lat, lng FROM hive_table WHERE lat > 0 AND lng > 0")
    fun getHivesLocations(): Flow<List<HiveLocationModel>>

    @Query("SELECT id, name, lat, lng FROM hive_table WHERE id = :id")
    fun getLocationByHiveId(id: Int): Flow<List<HiveLocationModel>>

    @Query("UPDATE hive_table SET lat = :lat, lng = :lng WHERE id = :id")
    fun updateHiveLocation(id: Int, lat: Double, lng: Double)
}