package com.example.hiveapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.hiveapp.data.model.HiveLocationModel
import com.example.hiveapp.data.model.HiveModel

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
    suspend fun getHiveById(id: Int): HiveModel?

    @Query("SELECT id, name, lat, lng FROM hive_table WHERE lat > 0 AND lng > 0")
    suspend fun getHivesLocations(): List<HiveLocationModel>

    @Query("SELECT id, name, lat, lng FROM hive_table WHERE id = :id")
    suspend fun getLocationByHiveId(id: Int): List<HiveLocationModel>

    @Query("UPDATE hive_table SET lat = :lat, lng = :lng WHERE id = :id")
    suspend fun updateHiveLocation(id: Int, lat: Double, lng: Double)
}